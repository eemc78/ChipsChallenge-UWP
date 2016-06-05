namespace ChipsChallenge.Features
{
    using System;

    using Windows.UI.Xaml.Controls;

    public class MenuItem
    {
        public MenuItem(Symbol symbol, string title, Type view)
        {
            Symbol = symbol;
            Title = title;
            View = view;
        }

        public Symbol Symbol { get; set; }

        public string Title { get; set; }

        public Type View { get; set; }
    }
}