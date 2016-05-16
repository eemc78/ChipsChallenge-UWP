namespace ChipsChallenge.Features
{
    using System.Collections.Generic;

    using Windows.UI.Xaml.Controls;

    public class Menu
    {
        public List<MenuItem> MenuItems { get; set; }
        public Menu()
        {
            MenuItems = new List<MenuItem>
            {
                new MenuItem(Symbol.Back, "Back to game", typeof(Game.Game)),
                new MenuItem(Symbol.AllApps, "Level", typeof(Levels.Levels)),
                new MenuItem(Symbol.Setting, "Settings", typeof(Settings.Settings))
            };
        }
    }
}