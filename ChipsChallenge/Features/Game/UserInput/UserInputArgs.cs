namespace ChipsChallenge.Features.Game.UserInput
{
    using System;

    using Shared.Gui;

    public class UserInputArgs : EventArgs
    {
        public UserInput Input { get; set; }
    }
}