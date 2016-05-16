namespace ChipsChallenge.Features.Game.UserInput
{
    using System;

    using ChipsChallenge.Shared.Gui;

    public class UserInputArgs : EventArgs
    {
        public UserInput Input { set; get; }
    }
}