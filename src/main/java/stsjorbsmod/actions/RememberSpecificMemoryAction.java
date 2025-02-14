package stsjorbsmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stsjorbsmod.memories.AbstractMemory;
import stsjorbsmod.memories.MemoryManager;

public class RememberSpecificMemoryAction extends AbstractGameAction  {
    private String memoryID;

    public RememberSpecificMemoryAction(AbstractCreature target, String memoryID) {
        this.setValues(target, target);
        this.memoryID = memoryID;
    }

    public void update() {
        MemoryManager.forPlayer(target).rememberMemory(memoryID);
        isDone = true;
    }
}
