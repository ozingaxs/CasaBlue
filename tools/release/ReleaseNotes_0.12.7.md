# Release Notes - Version 0.12.7 (CasaBlue)

This release focuses on critical performance optimizations, especially for low-end and low-RAM devices (like J7 Prime).

## Key Improvements

### 🚀 Startup Speed
- **Non-blocking Initializers:** Moved heavy configuration tasks (tracing, preferences prefetch) to background threads. The app now starts drawing the UI without waiting for disk I/O.
- **Lazy Emoji Loading:** Emoji system initialization is now asynchronous, preventing UI hangs during font loading.
- **Priority Splash Screen:** Optimized the splash screen lifecycle to show immediately upon app launch.

### 🧠 Memory (RAM) Optimization
- **Low-RAM Mode:** Automatically detects devices with limited RAM (<= 1GB/2GB) and applies specialized constraints:
    - Reduced image cache size (Coil).
    - Enabled RGB_565 bitmap format to save 50% memory for avatars and thumbnails.
    - Reduced Rust SDK media cache size.
    - Limited I/O thread parallelism to prevent system resource exhaustion.
- **Dynamic Resource Management:** Implemented aggressive memory trimming when the app is in the background or the system is low on memory.

### 🛠 Feature Adjustments (Low-RAM devices)
The following memory-intensive features are now automatically disabled on low-RAM devices to ensure stability:
- Message Search
- Automatic Back-Pagination
- Multi-Account Support
- Thread Timelines

## Technical Changes
- Updated `versionCode` to `1240`.
- Updated `versionName` to `0.12.7-ex_26_9_1`.
- Introduced `LowRamFeatureFlagProvider` for automatic hardware-based feature management.
- Integrated `isLowRamDevice` check across UI and SDK layers.
