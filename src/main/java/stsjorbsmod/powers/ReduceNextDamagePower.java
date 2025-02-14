package stsjorbsmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import stsjorbsmod.JorbsMod;
import stsjorbsmod.util.TextureLoader;

import static stsjorbsmod.JorbsMod.makePowerPath;

public class ReduceNextDamagePower extends AbstractPower {
    public static final String POWER_ID = JorbsMod.makeID(ReduceNextDamagePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("damage_reduction_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("damage_reduction_power32.png"));

    public ReduceNextDamagePower(AbstractCreature owner, int damageReduction) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = damageReduction;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int originalAmount) {
        // Note: this applies after block, weaken, vulnerable, etc are all calculated
        if (info.type == DamageType.NORMAL) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
            return this.amount > originalAmount ? 0 : originalAmount - this.amount;
        } else {
            return originalAmount;
        }
    }
}
