﻿namespace ChipsChallenge.Shared.Blockreactions
{
    public class BrownButtonFrom : NoSlipReaction
    {
        private static BrownButtonFrom instance;

        private BrownButtonFrom()
        {
        }

        public static BrownButtonFrom Instance
        {
            get
            {
                lock (typeof(BrownButtonFrom))
                {
                    return instance ?? (instance = new BrownButtonFrom());
                }
            }
        }

        public override void React(Block moving, Block standing)
        {
            Buttons.BrownButtonUp(standing);
        }

        public override bool canMove(Block moving, Block standing)
        {
            return true;
        }
    }
}