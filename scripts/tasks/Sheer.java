package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import scripts.Task;

import java.util.concurrent.Callable;

public class Sheer extends Task {

    final static int Sheep_IDS[] = {2794, 2795, 2796, 2800, 2801, 2802};
    public Sheer(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
            return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28;
    }

    @Override
    public void execute() {

        final Npc sheeptosheer = ctx.npcs.select().id(Sheep_IDS).nearest().poll();
        System.out.println("sheering sheep");
            if(sheeptosheer.inViewport()) {
            sheeptosheer.interact("Shear");
        } else {
            ctx.movement.step(sheeptosheer);
            ctx.camera.turnTo(sheeptosheer);
        }
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation()!=-1;
            }
        },200,10);

    }
}
