namespace ChipsChallenge.Shared.Blockreactions
{
    using Boots = Inventory.Boots;
    using Type = Block.Type;

    public class FireTo : NoSlipReaction
    {
        private FireTo()
        {
        }
        private static FireTo instance;

        public static FireTo Instance
        {
            get
            {
                lock (typeof(FireTo))
                {
                    if (instance == null)
                    {
                        instance = new FireTo();
                    }
                    return instance;
                }
            }
        }

        // Kills all enemies except fireball
        public override void React(Block moving, Block standing)
        {
            switch (moving.getType())
            {
                case Type.CHIP:
                    if (!HasBoots(Boots.FIREBOOTS))
                    {
                        moving.Destroy();
                        standing.Replace(CreateBlock(Type.BURNEDCHIP));
                        Die("Ooops! Don't step in the fire without fire boots!");
                    }
                    break;
                case Type.BUG:
                case Type.TEETH:
                case Type.PINKBALL:
                case Type.TANK:
                case Type.BLOB:
                    moving.Destroy();
                    break;
            }
        }

        // Everyone can move here except bugs or walkers
        public override bool canMove(Block moving, Block standing)
        {
            return !(moving.IsA(Type.BUG) || moving.IsA(Type.WALKER));
        }
    }
}