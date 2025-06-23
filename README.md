# 🎬 Android Movie List App

A modern Android application built with Jetpack Compose featuring advanced swipe gestures, Material Design 3, and sophisticated state management. Demonstrates cutting-edge mobile development with professional UX patterns and responsive design.

## 🎯 What I Built
- **Modern Android App**: Built with the latest Jetpack Compose and Material Design 3
- **Advanced Swipe Gestures**: Swipe right to delete, swipe left to edit with visual feedback
- **Professional State Management**: MVVM architecture with ViewModel and Compose state
- **Dynamic UI Components**: Responsive layouts with keyboard control and animations
- **Complete CRUD Operations**: Add, edit, delete, and restore movies with ratings
- **Archive System**: Restore deleted movies with undo functionality

## ✨ Key Features

### User Experience
- 📱 **Intuitive Swipe Controls**: Natural swipe gestures for delete (red) and edit (green)
- ⚡ **Real-Time Updates**: Immediate UI response with smooth animations
- 🎨 **Material Design 3**: Modern theming with adaptive colors and typography
- ⌨️ **Smart Keyboard**: Auto-hide keyboard on form submission
- 🔄 **Edit-in-Place**: Pre-populated forms for seamless movie editing

### Movie Management
- ➕ **Add Movies**: Create entries with title, runtime, and MPAA ratings
- ✏️ **Edit Movies**: Tap and edit existing entries with preserved data
- 🗑️ **Delete Movies**: Swipe right to remove with visual confirmation
- 📦 **Archive System**: Restore accidentally deleted movies
- 📊 **Rating System**: Radio button selection for G, PG, PG-13, R ratings

### Technical Features
- 🚀 **Lazy Loading**: Efficient scrolling with LazyColumn for large lists
- 🎭 **State Persistence**: Proper state management across screen rotations
- 📱 **Responsive Design**: Adaptive layouts for different screen sizes
- 🎨 **Custom Animations**: Smooth item placement and transition effects

## 🏗️ Technical Architecture

```
Android Movie List App
├── UI Layer (Jetpack Compose)
│   ├── ToDoScreen (Main composable)
│   ├── TaskList (LazyColumn with swipe gestures)
│   ├── TaskCard (Movie item display)
│   ├── AddTaskInput (Form with validation)
│   └── SwipeBackground (Visual feedback)
├── ViewModel Layer
│   ├── ToDoViewModel (State management)
│   ├── Task data class (Movie model)
│   └── Business logic operations
└── State Management
    ├── Compose state (remember, mutableStateOf)
    ├── ViewModel state persistence
    └── Swipe gesture state tracking
```

### Component Hierarchy
```
ToDoScreen
├── ToDoAppTopBar (Navigation and actions)
├── AddTaskInput (Movie form with validation)
│   ├── OutlinedTextField (Title and runtime)
│   └── RadioGroup (MPAA rating selection)
└── TaskList (Scrollable movie list)
    └── SwipeToDismissBox (Gesture handling)
        ├── SwipeBackground (Visual feedback)
        └── TaskCard (Movie display)
```

## 🔧 Advanced Implementation Details

### Jetpack Compose Features
- **State Management**: `remember`, `mutableStateOf`, `rememberUpdatedState`
- **Lazy Lists**: `LazyColumn` with key-based item management
- **Gesture Handling**: `SwipeToDismissBox` with directional feedback
- **Material Design**: `Scaffold`, `TopAppBar`, `Card`, `OutlinedTextField`
- **Animations**: `animateItemPlacement` for smooth transitions

### Modern Android Patterns
- **MVVM Architecture**: Clean separation of UI and business logic
- **Declarative UI**: Compose's reactive programming model
- **State Hoisting**: Proper state management up the component tree
- **Keyboard Management**: `LocalSoftwareKeyboardController` integration
- **Material Theming**: Dynamic color schemes and typography

### UX Design Patterns
- **Swipe-to-Action**: Industry-standard gesture patterns
- **Visual Feedback**: Color-coded swipe backgrounds (red/green)
- **Form Validation**: Real-time input handling and validation
- **Undo Functionality**: Archive system for accidental deletions
- **Responsive Layouts**: Adaptive design for various screen sizes

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.8+
- Android SDK 24+
- Jetpack Compose BOM 2023.x

### Installation
```bash
# Clone the repository
git clone https://github.com/AGButt04/Movies-App.git

# Open in Android Studio
# File -> Open -> Select project directory

# Sync Gradle files
# Build -> Clean Project
# Build -> Rebuild Project

# Run on device or emulator
# Run -> Run 'app'
```

## 🔧 Technologies & Frameworks

### Core Technologies
- **Kotlin** - Modern Android development language
- **Jetpack Compose** - Declarative UI toolkit
- **Material Design 3** - Modern design system
- **Android Architecture Components** - ViewModel, State management

### Compose Libraries
- **Foundation** - Basic building blocks and layout
- **Material3** - Material Design components
- **Runtime** - State management and composition
- **UI** - Core UI components and modifiers

### Advanced Features
- **SwipeToDismissBox** - Gesture-based interactions
- **LazyColumn** - Performance-optimized scrolling
- **Scaffold** - Screen structure with app bars
- **OutlinedTextField** - Material text input components
- **RadioButton** - Selection input components

## 💡 Technical Highlights

### State Management Excellence
- **Reactive State**: Compose's declarative state management
- **State Hoisting**: Proper data flow up the component hierarchy
- **ViewModelIntegration**: Professional architecture patterns
- **State Preservation**: Maintains state across configuration changes

### Advanced Compose Techniques
- **Custom Composables**: Reusable UI components with proper encapsulation
- **Gesture Recognition**: Complex swipe gesture handling with visual feedback
- **Animation Integration**: Smooth transitions and item placement animations
- **Performance Optimization**: Efficient recomposition and lazy loading

### Professional UX Implementation
- **Material Design Compliance**: Following Google's design guidelines
- **Accessibility Support**: Proper semantic roles and descriptions
- **Keyboard Management**: Smart keyboard show/hide functionality
- **Error Prevention**: Input validation and confirmation dialogs

## 📖 Learning Outcomes

This project demonstrates mastery of:
- **Modern Android Development**: Latest tools and architectural patterns
- **Jetpack Compose**: Declarative UI programming with advanced features
- **Mobile UX Design**: Gesture-based interactions and responsive layouts
- **State Management**: Complex state handling in reactive UI frameworks
- **Material Design**: Professional visual design implementation
- **Performance Optimization**: Efficient mobile app development practices

## 🎮 User Workflows

### Adding Movies
1. **Tap "ADD MOVIE"**: Opens input form with title, runtime, and rating fields
2. **Enter Details**: Type movie title and runtime duration
3. **Select Rating**: Choose MPAA rating (G, PG, PG-13, R) via radio buttons
4. **Submit**: Tap "ADD TASK" to save movie to list

### Managing Movies
1. **View List**: Scroll through movies with lazy loading performance
2. **Edit Movie**: Swipe left (green background) to edit the existing entry
3. **Delete Movie**: Swipe right (red background) to remove from list
4. **Restore Movie**: Use the top bar restore button to undo deletions

### Advanced Features
1. **Bulk Operations**: Top bar actions for creating test data
2. **Archive Management**: Restore system for deleted movies
3. **State Persistence**: Maintains data across app lifecycle events

---
**Modern Android development showcase** | [GitHub Profile](https://github.com/AGButt04) | [LinkedIn](https://www.linkedin.com/in/abdul-ghani-butt-290056338/)

*Built with cutting-edge Android technologies including Jetpack Compose and Material Design 3*
