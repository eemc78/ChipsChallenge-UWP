namespace ChipsChallenge.Shared
{
    using System.Collections.Generic;
    using System.Linq;

    public class Buttons
    {
        private static readonly ICollection<Block> GreenButtonsListeners = new List<Block>();
        private static readonly ICollection<Block> BlueButtonsListeners = new List<Block>();
        private static readonly IDictionary<Block, ICollection<Block>> RedButtonListeners = new Dictionary<Block, ICollection<Block>>();
        private static readonly IDictionary<Block, ICollection<Block>> BrownButtonListeners = new Dictionary<Block, ICollection<Block>>();
        private static bool greenButton;
        private static bool blueButton;

        public static void Clear()
        {
            GreenButtonsListeners.Clear();
            BlueButtonsListeners.Clear();
            RedButtonListeners.Clear();
            BrownButtonListeners.Clear();
        }

        public static void AddGreenButtonsListener(Block listener)
        {
            GreenButtonsListeners.Add(listener);
        }

        public static void AddBlueButtonsListener(Block listener)
        {
            BlueButtonsListeners.Add(listener);
        }

        public static void RemoveGreenButtonsListener(Block listener)
        {
            GreenButtonsListeners.Remove(listener);
        }

        public static void RemoveBlueButtonsListener(Block listener)
        {
            BlueButtonsListeners.Remove(listener);
        }

        public static void AddRedButtonListener(Block button, Block listener)
        {
            ICollection<Block> listeners;
            RedButtonListeners.TryGetValue(button, out listeners);
            if (listeners == null)
            {
                listeners = new List<Block>();
                RedButtonListeners[button] = listeners;
            }
            
            listeners.Add(listener);
        }

        public static void AddBrownButtonListener(Block button, Block listener)
        {
            ICollection<Block> listeners;
            BrownButtonListeners.TryGetValue(button, out listeners);
            if (listeners == null)
            {
                listeners = new List<Block>();
                BrownButtonListeners[button] = listeners;
            }
            
            listeners.Add(listener);
        }

        public static void GreenButtonDown(Block button)
        {
            greenButton = !greenButton;
        }

        public static void BlueButtonDown(Block button)
        {
            blueButton = !blueButton;
        }

        public static void UpdateGreenandBlueButtons()
        {
            if (greenButton)
            {
                foreach (Block b in GreenButtonsListeners.ToList())
                {
                    b.ButtonDown(null);
                }
            }

            if (blueButton)
            {
                foreach (Block b in BlueButtonsListeners.ToList())
                {
                    b.ButtonDown(null);
                }
            }

            greenButton = false;
            blueButton = false;
        }

        public static void RedButtonDown(Block button)
        {
            ICollection<Block> listeners;
            RedButtonListeners.TryGetValue(button, out listeners);
            if (listeners != null)
            {
                foreach (Block b in listeners)
                {
                    b.ButtonDown(button);
                }
            }
        }

        public static void BrownButtonDown(Block button)
        {
            ICollection<Block> listeners;
            BrownButtonListeners.TryGetValue(button, out listeners);
            if (listeners != null)
            {
                foreach (Block b in listeners)
                {
                    b.ButtonDown(button);
                }
            }
        }

        public static void BrownButtonUp(Block button)
        {
            ICollection<Block> listeners;
            BrownButtonListeners.TryGetValue(button, out listeners);
            if (listeners != null)
            {
                foreach (Block b in listeners)
                {
                    b.ButtonUp(button);
                }
            }
        }
    }
}