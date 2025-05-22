package com.zybooks.todolistapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.reflect.KFunction3

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ToDoScreen(modifier: Modifier = Modifier, toDoViewModel: ToDoViewModel = viewModel()) {
    var showInput by remember {mutableStateOf(false)}
    var currentTask by remember {mutableStateOf<Task?>(null)}
    Scaffold( //It stops the bar from overlapping with the app
        topBar = {
            ToDoAppTopBar(
                completedTasksExist = toDoViewModel.completedTasksExist,
                archivedTasksExist = toDoViewModel.archiveTasksExist,
                onCreateTasks = toDoViewModel::createTestTasks,
                onRestoreArchive = toDoViewModel::restoreArchivedTasks
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(innerPadding)) {
            Button(
                onClick = { showInput = !showInput
                          if (!showInput) currentTask = null },
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            ) {
                Text(text = if (showInput)  "CANCEL" else "ADD MOVIE")
            }

            if (showInput) {
                AddTaskInput(
                    initialBody = currentTask?.body.orEmpty(),
                    initialLength = currentTask?.completed.orEmpty(),
                    initialRating = currentTask?.rating.orEmpty(),
                    onEnterTask = { body, length, rating ->
                        if (currentTask != null) {
                            toDoViewModel.updateTask(currentTask!!.id.toString(), body, length, rating)
                        } else {
                            toDoViewModel.addTask(body, length, rating)
                        }
                        currentTask = null
                        showInput = false
                    }
                )
            }

            TaskList(
                taskList = toDoViewModel.taskList,
                onDeleteTask = toDoViewModel::deleteTask,
                editingTask = {
                    task -> currentTask = task
                    showInput = true
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoAppTopBar(
    completedTasksExist: Boolean,
    archivedTasksExist: Boolean,
    onCreateTasks: () -> Unit,
    onRestoreArchive: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {Text("Movie List")},
        actions = {
            IconButton(onClick = onCreateTasks) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Random Movies"
                )
            }
            IconButton(onClick = onRestoreArchive, enabled = archivedTasksExist) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Restore Deleted Movie"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    taskList: List<Task>,
    onDeleteTask: (Task) -> Unit,
    editingTask: (Task) -> Unit
) {
    LazyColumn {
        items(
            items = taskList,
            key = {task -> task.id}
        ) { task ->
            val currentTask by rememberUpdatedState(task)
            val dismissState = rememberSwipeToDismissBoxState(
                //Trying to remember the state of the swipe box
                confirmValueChange = { //Did the state change or not?
                    when (it) { //Like switch statement
                        SwipeToDismissBoxValue.StartToEnd -> {
                            onDeleteTask(currentTask)
                            true
                        }
                        SwipeToDismissBoxValue.EndToStart -> {
                            editingTask(currentTask)
                            false
                        }
                        else -> false
                    }
                }
            )
            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = { SwipeBackground((dismissState)) }, //behind the item you're swiping
                modifier = Modifier.padding(vertical = 1.dp).animateItemPlacement(),
                enableDismissFromStartToEnd = true, //swipe right
                enableDismissFromEndToStart = true, //swipe left
                content = { //What is inside the swipe box, what you're swiping.
                    TaskCard(
                        task = task,
                    )
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeBackground(dismissState: SwipeToDismissBoxState, modifier: Modifier = Modifier) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> Color.Green
        SwipeToDismissBoxValue.StartToEnd -> Color.Red
        else -> Color.Transparent
    }
    Row (
        modifier = modifier.fillMaxSize().background(color).padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        if (dismissState.dismissDirection == SwipeToDismissBoxValue.StartToEnd) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
        Spacer(modifier = Modifier)
        if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
            Icon(
                painter = painterResource(R.drawable.archive),
                contentDescription = "Archive"
            )
        }
    }
}

@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(8.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.body,
                modifier = modifier.padding(start = 12.dp),
            )
            Text(
                text = task.completed + ". " + task.rating,
                modifier = modifier.padding(end = 10.dp)
            )
        }
    }
}

@Composable
fun AddTaskInput(
    initialBody: String = "",
    initialRating: String = "",
    initialLength: String = "",
    onEnterTask: (String, String, String) -> Unit
) { //Unit means the function doesn't return anything, it just does something.
    val keyboardController =
        LocalSoftwareKeyboardController.current //Pops up and disappears when hit enter
    var taskBody by remember { mutableStateOf(initialBody) }
    var taskRating by remember { mutableStateOf(initialRating) }
    var taskLength by remember { mutableStateOf(initialLength)}

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        value = taskBody,
        onValueChange = { taskBody = it },
        label = { Text("Enter Task") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), //Defines an action based of a specific key
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(6.dp),
        value = taskLength,
        onValueChange = { taskLength = it },
        label = { Text("Enter Runtime") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), //Defines an action based of a specific key
    )
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
        RadioGroup(
            radioOptions = listOf("(G)", "(PG)", "(PG-13)", "(R)"),
            selectedOption = taskRating,
            onSelected = { taskRating = it },
        )
        Button(
            onClick = {
                onEnterTask(taskBody, taskLength, taskRating)
                keyboardController?.hide()
            },
        ) {
            Text("ADD TASK")
        }
    }
}

@Composable
fun RadioGroup(
    radioOptions: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelectedOption: (String) -> Boolean = { selectedOption == it }
    Column(modifier = modifier) {
        Row {
            radioOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = isSelectedOption(option),
                            onClick = { onSelected(option) },
                            role = Role.RadioButton
                        )
                        .padding(start = 6.dp, end = 6.dp, top = 4.dp),
                ) {
                    RadioButton(
                        selected = isSelectedOption(option),
                        onClick = null,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(text = option, modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}