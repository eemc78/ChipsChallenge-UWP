namespace ChipsChallenge.Shared
{
    using System.Collections.Generic;

    using Microsoft.Graphics.Canvas;

    using Moves = Move.Moves;
    using Type = Block.Type;

    public class BlockImageFactory
    {
        private static BlockImageFactory instance;
        private readonly IDictionary<Type, IDictionary<Moves, CanvasBitmap>> loadedImages = new Dictionary<Type, IDictionary<Moves, CanvasBitmap>>();
        private readonly IDictionary<Type, IDictionary<Moves, CanvasBitmap>> loadedImagesOverlay = new Dictionary<Type, IDictionary<Moves, CanvasBitmap>>();
        private CanvasBitmap blockSprites;

        private BlockImageFactory()
        {
        }

        public static BlockImageFactory Instance => instance ?? (instance = new BlockImageFactory());

        public void Initialize(CanvasBitmap sprites)
        {
            blockSprites = sprites;
        }

        public virtual CanvasBitmap GetImage(Type type, Moves moves, bool overlay)
        {
            var images = overlay ? loadedImagesOverlay : loadedImages;

            IDictionary<Moves, CanvasBitmap> moveImages;
            images.TryGetValue(type, out moveImages);
            if (moveImages == null)
            {
                moveImages = new Dictionary<Moves, CanvasBitmap>();
                images[type] = moveImages;
            }

            CanvasBitmap im;
            moveImages.TryGetValue(moves, out im);
            if (im == null)
            {
                im = LoadImage(type, moves, overlay);
                moveImages[moves] = im;
            }

            return im;
        }

        private CanvasBitmap LoadImage(Type type, Moves moves, bool overlay)
        {
            // If overlay, x and y are the overlay coordinates.
            int x = 0;
            int y = 0;

            switch (type)
            {
                case Type.BLOB:
                    y = 12;
                    x = 5;
                    break;
                case Type.BLOCK:
                    y = 10;
                    x = 0;
                    break;
                case Type.BLUEBUTTON:
                    y = 8;
                    x = 2;
                    break;
                case Type.BLUEKEY:
                    y = 4;
                    x = 6;
                    break;
                case Type.BLUELOCK:
                    y = 6;
                    x = 1;
                    break;
                case Type.BLUEWALLREAL:
                case Type.BLUEWALLFAKE:
                    y = 14;
                    x = 1;
                    break;
                case Type.BOMB:
                    y = 10;
                    x = 2;
                    break;
                case Type.BROWNBUTTON:
                    y = 7;
                    x = 2;
                    break;
                case Type.BUG:
                    y = 0;
                    x = 4;
                    break;
                case Type.BURNEDCHIP:
                    y = 4;
                    x = 3;
                    break;
                case Type.CHIP:
                    y = 12;
                    x = 6;
                    break;
                case Type.CLONEBLOCK:
                    y = 0;
                    x = 1;
                    break;
                case Type.CLONEMACHINE:
                    y = 1;
                    x = 3;
                    break;
                case Type.COMPUTERCHIP:
                    y = 2;
                    x = 0;
                    break;
                case Type.DIRT:
                    y = 11;
                    x = 0;
                    break;
                case Type.DROWNEDCHIP:
                    y = 3;
                    x = 3;
                    break;
                case Type.EXIT:
                    y = 5;
                    x = 1;
                    break;
                case Type.FAKEEXIT:
                    y = 5;
                    x = 1;
                    break;
                case Type.FIRE:
                    y = 4;
                    x = 0;
                    break;
                case Type.FIREBOOTS:
                    y = 9;
                    x = 6;
                    break;
                case Type.FIREBALL:
                    y = 4;
                    x = 4;
                    break;
                case Type.FLIPPERS:
                    y = 8;
                    x = 6;
                    break;
                case Type.FLOOR:
                    y = 0;
                    x = 0;
                    break;
                case Type.FORCEFLOOR:
                    y = 2;
                    x = 1;
                    break;
                case Type.RANDOMFORCEFLOOR:
                    y = 2;
                    x = 3;
                    break;
                case Type.GLIDER:
                    y = 0;
                    x = 5;
                    break;
                case Type.GRAVEL:
                    y = 13;
                    x = 2;
                    break;
                case Type.GREENBUTTON:
                    y = 3;
                    x = 2;
                    break;
                case Type.GREENKEY:
                    y = 6;
                    x = 6;
                    break;
                case Type.GREENLOCK:
                    y = 8;
                    x = 1;
                    break;
                case Type.HIDDENWALL:
                    y = 0;
                    x = 0;
                    break;
                case Type.HINT:
                    y = 15;
                    x = 2;
                    break;
                case Type.ICE:
                    y = 12;
                    x = 0;
                    break;
                case Type.ICECORNER:
                    y = 10;
                    x = 1;
                    break;
                case Type.ICEBLOCK:
                    y = 10;
                    x = 1;
                    break;
                case Type.ICESKATES:
                    y = 10;
                    x = 6;
                    break;
                case Type.INVISIBLEWALL:
                    y = 0;
                    x = 0;
                    break;
                case Type.LOCK: // ?
                    break;
                case Type.PARAMECIUM:
                    y = 0;
                    x = 6;
                    break;
                case Type.PINKBALL:
                    y = 8;
                    x = 4;
                    break;
                case Type.RECESSEDWALL:
                    y = 14;
                    x = 2;
                    break;
                case Type.REDBUTTON:
                    y = 4;
                    x = 2;
                    break;
                case Type.REDKEY:
                    y = 5;
                    x = 6;
                    break;
                case Type.REDLOCK:
                    y = 7;
                    x = 1;
                    break;
                case Type.SOCKET:
                    y = 2;
                    x = 2;
                    break;
                case Type.SUCTIONBOOTS:
                    y = 11;
                    x = 6;
                    break;
                case Type.SWIMMINGCHIP:
                    y = 12;
                    x = 3;
                    break;
                case Type.TANK:
                    y = 12;
                    x = 4;
                    break;
                case Type.TEETH:
                    y = 4;
                    x = 5;
                    break;
                case Type.TELEPORT:
                    y = 9;
                    x = 2;
                    break;
                case Type.THIEF:
                    y = 1;
                    x = 2;
                    break;
                case Type.THINWALL:
                    y = 6;
                    x = 0;
                    break;
                case Type.THINWALLSE:
                    y = 0;
                    x = 3;
                    break;
                case Type.TOGGLEWALLCLOSED:
                    y = 5;
                    x = 2;
                    break;
                case Type.TOGGLEWALLOPEN:
                    y = 6;
                    x = 2;
                    break;
                case Type.TRAP:
                    y = 11;
                    x = 2;
                    break;
                case Type.WALKER:
                    y = 8;
                    x = 5;
                    break;
                case Type.WALL:
                    y = 1;
                    x = 0;
                    break;
                case Type.WATER:
                    y = 3;
                    x = 0;
                    break;
                case Type.YELLOWKEY:
                    y = 7;
                    x = 6;
                    break;
                case Type.YELLOWLOCK:
                    y = 9;
                    x = 1;
                    break;
            }

            if (overlay)
            {
                if (x >= 4)
                {
                    x += 3;
                }
            }

            if ((x >= 4 && !(x == 6 && y >= 4 && y <= 11)) || type == Type.SWIMMINGCHIP || type == Type.ICEBLOCK || type == Type.THINWALL || type == Type.ICECORNER)
            {
                switch (moves)
                {
                    case Moves.DOWN:
                        y += 2;
                        break;
                    case Moves.LEFT:
                        y += 1;
                        break;
                    case Moves.RIGHT:
                        y += 3;
                        break;
                }
            }

            if (type == Type.FORCEFLOOR)
            {
                switch (moves)
                {
                    case Moves.DOWN:
                        x = 0;
                        y = 13;
                        break;
                    case Moves.LEFT:
                        y += 2;
                        break;
                    case Moves.RIGHT:
                        y += 1;
                        break;
                }
            }

            CanvasDevice device = CanvasDevice.GetSharedDevice();
            CanvasRenderTarget image = new CanvasRenderTarget(device, 32, 32, 96);
            image.CopyPixelsFromBitmap(blockSprites, 0, 0, x * 32, y * 32, 32, 32);

            IDictionary<Type, IDictionary<Moves, CanvasBitmap>> images;
            if (overlay)
            {
                images = loadedImagesOverlay;
            }
            else
            {
                images = loadedImages;
            }

            IDictionary<Moves, CanvasBitmap> movesMap = images[type];
            if (movesMap == null)
            {
                movesMap = new Dictionary<Moves, CanvasBitmap>();
                movesMap[moves] = image;
                images[type] = movesMap;
            }
            else
            {
                movesMap[moves] = image;
            }

            return image;
        }
    }
}