namespace ChipsChallenge.Features.Game.UserInput
{
    using System.Collections.Generic;
    using System.Linq;

    using Shared.Gui;

    using Windows.Gaming.Input;

    public class GamePadInput : UserInputBase
    {
        private readonly Dictionary<GamepadButtons, UserInput> buttonMapping;

        private Gamepad gamepad;

        public GamePadInput()
        {
            buttonMapping = new Dictionary<GamepadButtons, UserInput>
                                     {
                                         { GamepadButtons.DPadUp, UserInput.MoveUp },
                                         { GamepadButtons.DPadDown, UserInput.MoveDown },
                                         { GamepadButtons.DPadLeft, UserInput.MoveLeft },
                                         { GamepadButtons.DPadRight, UserInput.MoveRight },
                                         { GamepadButtons.A, UserInput.TogglePause },
                                         { GamepadButtons.B, UserInput.RestartLevel },
                                         { GamepadButtons.X, UserInput.NextLevel },
                                         { GamepadButtons.Y, UserInput.PreviousLevel }
                                     };

            Gamepad.GamepadAdded += GamepadAdded;
            Gamepad.GamepadRemoved += GamepadRemoved;
        }

        public void ReadGamePadInput()
        {
            if (gamepad != null)
            {
                GamepadReading reading = gamepad.GetCurrentReading();

                if (reading.Buttons != GamepadButtons.None)
                {
                    if (buttonMapping.ContainsKey(reading.Buttons))
                    {
                        OnUserInputReceived(buttonMapping[reading.Buttons]);
                    }
                }
                else
                {
                    // Use 0.4 so the stick don't have to be exactly to right or left, but is more forgiving
                    if (reading.RightThumbstickX < -0.6)
                    {
                        OnUserInputReceived(UserInput.MoveLeft);
                    }
                    else if (reading.RightThumbstickX > 0.6)
                    {
                        OnUserInputReceived(UserInput.MoveRight);
                    }
                    else if (reading.RightThumbstickY > 0.6)
                    {
                        OnUserInputReceived(UserInput.MoveUp);
                    }
                    else if (reading.RightThumbstickY < -0.6)
                    {
                        OnUserInputReceived(UserInput.MoveDown);
                    }
                }
            }
        }

        private void GamepadAdded(object sender, Gamepad e)
        {
            gamepad = Gamepad.Gamepads.First();
        }

        private void GamepadRemoved(object sender, Gamepad e)
        {
            gamepad = null;
        }
    }
}