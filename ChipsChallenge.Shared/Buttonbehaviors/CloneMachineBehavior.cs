using System;

namespace ChipsChallenge.Shared.Buttonbehaviors
{
    /// <summary>
    /// If the button was red, clone what's on top of the listener
    /// </summary>
    public class CloneMachineBehavior : IButtonBehavior
    {
        private CloneMachineBehavior()
        {
        }

        private static CloneMachineBehavior instance;

        public static CloneMachineBehavior Instance
        {
            get
            {
                lock (typeof(CloneMachineBehavior))
                {
                    return instance ?? (instance = new CloneMachineBehavior());
                }
            }
        }

        public virtual void ButtonDown(Block listener, Block button)
        {
            if (button.IsA(Block.Type.REDBUTTON))
            {
                Game g = Game.Instance;
                GameLevel gl = g.Level;
                BlockContainer bc = gl.GetBlockContainer(listener);
                // Take the first creature and clone it
                Block b = bc.Upper;

                
                if (b.Creature || b.IsBlock())
                {
                    BlockContainer moveTo = gl.GetBlockContainer(b, b.Facing);
                    if (moveTo.CanMoveTo(b)) {
                        try 
                        {
                            Block clone = g.BlockFactory.Get(b.getType(), b.Facing);
                            Point p = b.Point;
                            //Move.updatePoint(p, b.getFacing());
                            if (clone.Creature && !(clone.IsA(Block.Type.TEETH) || clone.IsA(Block.Type.BLOB) || clone.IsA(Block.Type.FIREBALL))) 
                            {
                                g.AddBlockDelay(clone, p, 3);
                            }
                            else
                            {
                                gl.AddBlock(p.X, p.Y, clone, 2);
                                gl.MoveBlock(clone, clone.Facing, true, false);
                                if (clone.Creature) 
                                {
                                    Creatures.AddCreature(clone);
                                }
                            }
                        } 
                        catch (BlockContainerFullException)
                        {
                        // Ignore for now. TODO: Fix
                        }
                    }
                }
                 
                try
                {
                    b.Clone();
                    if (b.Creature)
                    {
                        Creatures.Boss = b;
                    }
                }
                catch (Exception)
                {
                    //System.out.println("Couldn't clone " + b);
                    // Ignore
                }
            }
        }

        public virtual void ButtonUp(Block listener, Block button)
        {
        }
    }
}