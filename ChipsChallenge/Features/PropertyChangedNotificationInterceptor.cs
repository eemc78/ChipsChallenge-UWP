namespace ChipsChallenge.Features
{
    using System;

    using Windows.UI.Core;

    public class PropertyChangedNotificationInterceptor
    {
        public static async void Intercept(object target, Action onPropertyChangedAction, string propertyName)
        {
            await Windows.ApplicationModel.Core.CoreApplication.MainView.CoreWindow.Dispatcher.RunAsync(CoreDispatcherPriority.Normal, () =>
            {
                onPropertyChangedAction();
            });
        }
    }
}