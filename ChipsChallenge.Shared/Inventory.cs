namespace ChipsChallenge.Shared
{
    using System;
    using System.Collections.Generic;

    public class Inventory
    {
        public enum Key
        {
            BLUE,
            GREEN,
            RED,
            YELLOW
        }

        public enum Boots
        {
            FIREBOOTS,
            FLIPPERS,
            ICESKATES,
            SUCTIONBOOTS
        }
        
        private readonly HashSet<Boots> boots = new HashSet<Boots>();
        private readonly ICollection<Key> keys = new List<Key>();

        public virtual void Clear()
        {
            boots.Clear();
            keys.Clear();
        }

        public virtual HashSet<Boots> GetBoots()
        {
            return boots;
        }

        public virtual ICollection<Key> Keys => keys;

        public virtual void TakeKey(Key type)
        {
            keys.Add(type);
        }

        public virtual void TakeBoots(Boots type)
        {
            boots.Add(type);
        }

        public virtual void UseKey(Key type)
        {
            if (type == Key.GREEN)
            {
                if (!keys.Contains(type))
                {
                    throw new Exception("Cannot use green key: no green key in invetory");
                }
            }
            else
            {
                keys.Remove(type);
            }
        }

        public virtual bool HasKey(Key type)
        {
            return keys.Contains(type);
        }

        public virtual bool HasBoots(Boots type)
        {
            return boots.Contains(type);
        }

        public virtual void ClearKeys()
        {
            keys.Clear();
        }

        public virtual void ClearBoots()
        {
            boots.Clear();
        }
    }
}