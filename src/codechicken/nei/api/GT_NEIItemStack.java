package codechicken.nei.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_NEIItemStack {
    public final Item mItem;
    public final byte mStackSize;
    public final short mMetaData;

    public GT_NEIItemStack(Item aItem, long aStackSize, long aMetaData) {
        mItem = aItem;
        mStackSize = (byte) aStackSize;
        mMetaData = (short) aMetaData;
    }

    public GT_NEIItemStack(ItemStack aStack) {
        this(aStack == null ? null : aStack.getItem(), aStack == null ? 0 : aStack.stackSize, aStack == null ? 0 : Items.feather.getDamage(aStack));
    }

    public GT_NEIItemStack(int aHashCode) {
        this(intToStack(aHashCode));
    }

    public final ItemStack toStack() {
        if (mItem == null) return null;
        return new ItemStack(mItem, 1, mMetaData);
    }

    @Override
    public boolean equals(Object aStack) {
        if (aStack == this) return true;
        if (aStack instanceof GT_NEIItemStack) {
            return ((GT_NEIItemStack) aStack).mItem == mItem && (((GT_NEIItemStack) aStack).mMetaData == mMetaData||((GT_NEIItemStack)aStack).mItem.isDamageable());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return stackToInt(toStack());
    }

    public static int stackToInt(ItemStack aStack) {
        if (aStack == null) return 0;
        return Item.getIdFromItem(aStack.getItem()) | (aStack.getItem().isDamageable()?0:(Items.feather.getDamage(aStack) << 16));

    }

    public static ItemStack intToStack(int aStack) {
        int tID = aStack & (~0 >>> 16), tMeta = aStack >>> 16;
        Item tItem = Item.getItemById(tID);
        if (tItem != null) return new ItemStack(tItem, 1, tMeta);
        return null;
    }
}