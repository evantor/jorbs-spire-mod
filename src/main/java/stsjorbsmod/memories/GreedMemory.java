package stsjorbsmod.memories;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import stsjorbsmod.JorbsMod;

public class GreedMemory extends AbstractMemory {
    public static final StaticMemoryInfo STATIC = StaticMemoryInfo.Load(GreedMemory.class);

    private static final int GOLD_PER_KILL = 10;

    public GreedMemory(final AbstractCreature owner) {
        super(STATIC, MemoryType.SIN, owner);
        setDescriptionPlaceholder("!M!", GOLD_PER_KILL);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (isPassiveEffectActive() && !m.hasPower(MinionPower.POWER_ID)) {
            JorbsMod.logger.info("Greed: gaining gold");
            AbstractDungeon.player.gainGold(GOLD_PER_KILL);
        }
    }
}
