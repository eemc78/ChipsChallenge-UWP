using System;

using Windows.ApplicationModel;
using Windows.ApplicationModel.Activation;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

namespace ChipsChallenge
{
    using Windows.Foundation.Metadata;
    using Windows.UI;
    using Windows.UI.ViewManagement;

    /// <summary>
    /// Provides application-specific behavior to supplement the default Application class.
    /// </summary>
    sealed partial class App
    {
        /// <summary>
        /// Initializes the singleton application object.  This is the first line of authored code
        /// executed, and as such is the logical equivalent of main() or WinMain().
        /// </summary>
        public App()
        {
            InitializeComponent();
            Suspending += OnSuspending;
        }

        /// <summary>
        /// Invoked when the application is launched normally by the end user.  Other entry points
        /// will be used such as when the application is launched to open a specific file.
        /// </summary>
        /// <param name="e">Details about the launch request and process.</param>
        protected override async void OnLaunched(LaunchActivatedEventArgs e)
        {
#if DEBUG
            if (System.Diagnostics.Debugger.IsAttached)
            {
                DebugSettings.EnableFrameRateCounter = true;
            }
#endif

            // If we have a phone contract, hide the status bar
            if (ApiInformation.IsApiContractPresent("Windows.Phone.PhoneContract", 1, 0))
            {
                var statusBar = StatusBar.GetForCurrentView();
                await statusBar.HideAsync();
            }

            Frame rootFrame = Window.Current.Content as Frame;

            // Do not repeat app initialization when the Window already has content,
            // just ensure that the window is active
            if (rootFrame == null)
            {
                // Create a Frame to act as the navigation context and navigate to the first page
                rootFrame = new Frame();

                rootFrame.NavigationFailed += OnNavigationFailed;

                if (e.PreviousExecutionState == ApplicationExecutionState.Terminated)
                {
                    //TODO: Load state from previously suspended application
                }

                // Place the frame in the current Window
                Window.Current.Content = rootFrame;
            }

            if (rootFrame.Content == null)
            {
                // When the navigation stack isn't restored navigate to the first page,
                // configuring the new page by passing required information as a navigation
                // parameter
                rootFrame.Navigate(typeof(Features.MainPage), e.Arguments);
            }
            // Ensure the current window is active
            Window.Current.Activate();

            SetTitleBarColor();
        }

        private static void SetTitleBarColor()
        {
            var titleBar = ApplicationView.GetForCurrentView().TitleBar;

            SolidColorBrush backgroundColor = Current.Resources["TitleBarBackgroundThemeBrush"] as SolidColorBrush;
            SolidColorBrush buttonHoverColor = Current.Resources["TitleBarButtonHoverThemeBrush"] as SolidColorBrush;
            SolidColorBrush buttonPressedColor = Current.Resources["TitleBarButtonPressedThemeBrush"] as SolidColorBrush;

            titleBar.BackgroundColor = backgroundColor?.Color;
            titleBar.ForegroundColor = Colors.White;
            titleBar.ButtonBackgroundColor = backgroundColor?.Color;
            titleBar.ButtonForegroundColor = Colors.White;
            titleBar.ButtonHoverBackgroundColor = buttonHoverColor?.Color;
            titleBar.ButtonHoverForegroundColor = Colors.White;
            titleBar.ButtonPressedBackgroundColor = buttonPressedColor?.Color;
            titleBar.ButtonPressedForegroundColor = Colors.White;
            titleBar.InactiveBackgroundColor = backgroundColor?.Color;
            titleBar.InactiveForegroundColor = Colors.White;
            titleBar.ButtonInactiveBackgroundColor = backgroundColor?.Color;
            titleBar.ButtonInactiveForegroundColor = Colors.White;
        }

        /// <summary>
        /// Invoked when Navigation to a certain page fails
        /// </summary>
        /// <param name="sender">The Frame which failed navigation</param>
        /// <param name="e">Details about the navigation failure</param>
        void OnNavigationFailed(object sender, NavigationFailedEventArgs e)
        {
            throw new Exception("Failed to load Page " + e.SourcePageType.FullName);
        }

        /// <summary>
        /// Invoked when application execution is being suspended.  Application state is saved
        /// without knowing whether the application will be terminated or resumed with the contents
        /// of memory still intact.
        /// </summary>
        /// <param name="sender">The source of the suspend request.</param>
        /// <param name="e">Details about the suspend request.</param>
        private void OnSuspending(object sender, SuspendingEventArgs e)
        {
            var deferral = e.SuspendingOperation.GetDeferral();
            //TODO: Save application state and stop any background activity
            deferral.Complete();
        }
    }
}