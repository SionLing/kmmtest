# Simple Click Throttling

## Overview
Simple wrapper for onClick functions that prevents rapid clicking with global 300ms throttling.

## Usage

### Wrap any onClick function
```kotlin
// Before
Button(onClick = { doSomething() })

// After
Button(onClick = throttled { doSomething() })
```

### Store throttled function
```kotlin
@Composable
fun MyComponent() {
    val handleClick = throttled { doSomething() }
    
    Button(onClick = handleClick) {
        Text("Click Me")
    }
}
```

## How It Works
- **Global 300ms throttling**: Prevents any click in the entire app within 300ms of the previous click
- **Simple wrapper**: Just wraps your existing onClick functions
- **Lightweight**: Single global state, minimal overhead
- **Compatible**: Works with all existing UI components

## Examples in Codebase

**Back button:**
```kotlin
val throttledBackClick = throttled(onBackClick)
BackHandler { throttledBackClick() }
```

**Navigation:**
```kotlin
IconButton(onClick = throttled(onAboutClick)) {
    Icon(Icons.Default.Info, "About")
}
```

**List items:**
```kotlin
PictureCard(
    onClick = throttled { onImageClick(picture.id) }
)
```

**Direct usage:**
```kotlin
Card(onClick = throttled { handleCardClick() })
```

That's it! Just one simple function to wrap all your click handlers. 🎉