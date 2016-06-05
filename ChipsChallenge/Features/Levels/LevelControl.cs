namespace ChipsChallenge.Features.Levels
{
    using System;
    using System.Windows.Input;

    using Windows.UI.Xaml.Controls;

    public class LevelControl
    {
        public LevelControl(Symbol symbol, string title, Action<object> execute, Predicate<object> canExecute)
        {
            Symbol = symbol;
            Title = title;
            Command = new DelegateCommand(execute, canExecute);
        }

        public Symbol Symbol { get; private set; }

        public string Title { get; private set; }

        public ICommand Command { get; }
    }
}