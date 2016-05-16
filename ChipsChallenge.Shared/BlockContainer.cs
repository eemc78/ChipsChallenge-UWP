namespace ChipsChallenge.Shared
{
    using Microsoft.Graphics.Canvas;

    using Moves = Move.Moves;

    public class BlockContainer
    {
        private Block upper;
        private Block lower;
        private Block visitorLow;
        private Block visitorHigh;

        public virtual Block Lower
        {
            get
            {
                return lower;
            }
            set
            {
                lower = value;
            }
        }

        public virtual Block Upper
        {
            get
            {
                return upper;
            }
            set
            {
                upper = value;
            }
        }

        public virtual Block VisitorHigh
        {
            get
            {
                return visitorHigh;
            }
            set
            {
                visitorHigh = value;
            }
        }


        public virtual Block VisitorLow
        {
            get
            {
                return visitorLow;
            }

            set
            {
                visitorLow = value;
            }
        }

        public override string ToString()
        {
            return "[" + upper + "," + lower + "," + visitorLow + "," + visitorHigh + "]";
        }

        public virtual Block Find(Block.Type type)
        {
            if (lower != null && lower.IsA(type))
            {
                return lower;
            }
            if (upper != null && upper.IsA(type))
            {
                return upper;
            }
            if (visitorLow != null && visitorLow.IsA(type))
            {
                return visitorLow;
            }
            if (visitorHigh != null && visitorHigh.IsA(type))
            {
                return visitorHigh;
            }
            return null;
        }

        private void Push(Block b, int layer)
        {
            switch (layer)
            {
                case 3:
                    if (visitorHigh == null)
                    {
                        visitorHigh = b;
                    }
                    else
                    {
                        Push(visitorHigh, 2);
                        visitorHigh = b;
                    }
                    break;
                case 2:
                    if (visitorLow == null)
                    {
                        visitorLow = b;
                    }
                    else
                    {
                        Push(visitorLow, 1);
                        visitorLow = b;
                    }
                    break;
                case 1:
                    if (upper == null)
                    {
                        upper = b;
                    }
                    else
                    {
                        Push(upper, 0);
                        upper = b;
                    }
                    break;
                case 0:
                    if (lower == null)
                    {
                        lower = b;
                    }
                    else
                    {
                        throw new BlockContainerFullException();
                    }
                    break;
            }
        }

        public virtual void Push(Block b)
        {
            Push(b, 3);
        }

        public virtual void Add(Block b)
        {
            if (upper == null)
            {
                upper = b;
            }
            else if (lower == null)
            {
                lower = b;
            }
            else if (visitorLow == null)
            {
                visitorLow = b;
            }
            else if (visitorHigh == null)
            {
                visitorHigh = b;
            }
            else
            {
                throw new BlockContainerFullException();
            }
        }

        public virtual void Remove(Block b)
        {
            if (visitorHigh == b)
            {
                visitorHigh = null;
            }
            else if (visitorLow == b)
            {
                visitorLow = null;
            }
            else if (upper == b)
            {
                upper = null;
            }
            else if (lower == b)
            {
                lower = null;
            }
        }

        public virtual bool StandsOnMoreThanFloor(Block b)
        {
            if ((b.Chip || b.Creature || b.IsBlock()) && !(b.IsA(Block.Type.SWIMMINGCHIP) || b.IsA(Block.Type.BURNEDCHIP)))
            {
                if (b == visitorHigh)
                {
                    if (visitorLow != null && !visitorLow.IsA(Block.Type.FLOOR))
                    {
                        return true;
                    }

                    if (upper != null && !upper.IsA(Block.Type.FLOOR))
                    {
                        return true;
                    }
                    if (lower != null && !lower.IsA(Block.Type.FLOOR))
                    {
                        return true;
                    }
                }
                else if (b == visitorLow)
                {
                    if (upper != null && !upper.IsA(Block.Type.FLOOR))
                    {
                        return true;
                    }

                    if (lower != null && !lower.IsA(Block.Type.FLOOR))
                    {
                        return true;
                    }
                }
                else if (b == upper)
                {
                    if (lower != null && !lower.IsA(Block.Type.FLOOR))
                    {
                        return true;
                    }
                }
            }

            return false;
        }

        public virtual CanvasBitmap Image => GetImage(false, 3);

        public virtual CanvasBitmap GetImage(bool overlay, int layer)
        {
            CanvasDevice device = CanvasDevice.GetSharedDevice();
            CanvasRenderTarget offscreen = new CanvasRenderTarget(device, 32, 32, 96);

            using (CanvasDrawingSession ds = offscreen.CreateDrawingSession())
            {
                if (lower != null)
                {
                    ds.DrawImage(lower.GetImage(false));
                }

                if (upper != null)
                {
                    if (lower.getType() == Block.Type.CLONEMACHINE)
                    {
                        ds.DrawImage(upper.GetImage(true));
                    }
                    else
                    {
                        ds.DrawImage(upper.GetImage(false));
                    }
                }

                if (visitorLow != null)
                {
                    ds.DrawImage(visitorLow.GetImage(true));
                }

                if (visitorHigh != null)
                {
                    ds.DrawImage(visitorHigh.GetImage(true));
                }
            }

            return offscreen;
        }

        public virtual bool CanMoveFrom(Block b)
        {
            if (lower != null)
            {
                if (!lower.FromReaction.canMove(b, lower))
                {
                    return false;
                }
            }
            if (upper != null)
            {
                if (!upper.FromReaction.canMove(b, upper))
                {
                    return false;
                }
            }
            if (visitorLow != null)
            {
                if (!visitorLow.FromReaction.canMove(b, visitorLow))
                {
                    return false;
                }
            }
            if (visitorHigh != null)
            {
                if (!visitorHigh.FromReaction.canMove(b, visitorHigh))
                {
                    return false;
                }
            }
            return true;
        }

        public virtual bool CanMoveTo(Block b)
        {
            if (lower != null)
            {
                if (!lower.ToReaction.canMove(b, lower))
                {
                    return false;
                }
            }
            if (upper != null)
            {
                if (!upper.ToReaction.canMove(b, upper))
                {
                    return false;
                }
            }
            if (visitorLow != null)
            {
                if (!visitorLow.ToReaction.canMove(b, visitorLow))
                {
                    return false;
                }
            }
            if (visitorHigh != null)
            {
                if (!visitorHigh.ToReaction.canMove(b, visitorHigh))
                {
                    return false;
                }
            }
            return true;
        }

        public virtual Moves? CausesSlipTo(Block b)
        {
            Moves? m = null;
            if (lower != null)
            {
                if ((m = lower.ToReaction.CausesSlip(b, lower)) != null)
                {
                    return m;
                }
            }
            if (upper != null)
            {
                if ((m = upper.ToReaction.CausesSlip(b, upper)) != null)
                {
                    return m;
                }
            }
            if (visitorLow != null)
            {
                if ((m = visitorLow.ToReaction.CausesSlip(b, visitorLow)) != null)
                {
                    return m;
                }
            }
            if (visitorHigh != null)
            {
                if ((m = visitorHigh.ToReaction.CausesSlip(b, visitorHigh)) != null)
                {
                    return m;
                }
            }
            return m;
        }

        public virtual Moves? CausesSlipFrom(Block b)
        {
            Moves? m = null;
            if (lower != null)
            {
                if ((m = lower.FromReaction.CausesSlip(b, lower)) != null)
                {
                    return m;
                }
            }
            if (upper != null)
            {
                if ((m = upper.FromReaction.CausesSlip(b, upper)) != null)
                {
                    return m;
                }
            }
            if (visitorLow != null)
            {
                if ((m = visitorLow.FromReaction.CausesSlip(b, visitorLow)) != null)
                {
                    return m;
                }
            }
            if (visitorHigh != null)
            {
                if ((m = visitorHigh.FromReaction.CausesSlip(b, visitorHigh)) != null)
                {
                    return m;
                }
            }
            return m;
        }

        public virtual void MoveFrom(Block b)
        {
            visitorHigh?.FromReaction.React(b, visitorHigh);
            visitorLow?.FromReaction.React(b, visitorLow);
            upper?.FromReaction.React(b, upper);
            lower?.FromReaction.React(b, lower);
        }

        public virtual void MoveTo(Block b)
        {
            visitorHigh?.ToReaction.React(b, visitorHigh);
            visitorLow?.ToReaction.React(b, visitorLow);
            upper?.ToReaction.React(b, upper);
            lower?.ToReaction.React(b, lower);
        }

        public virtual void ReplaceBlock(Block a, Block b)
        {
            if (visitorHigh != null && visitorHigh == a)
            {
                visitorHigh = b;
            }
            if (visitorLow != null && visitorLow == a)
            {
                visitorLow = b;
            }
            if (upper != null && upper == a)
            {
                upper = b;
            }
            if (lower != null && lower == a)
            {
                lower = b;
            }
            a.Destroy();
        }
    }
}