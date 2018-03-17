package scripts.tasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import scripts.Task;
import scripts.Walker;

public class Walk extends Task {

    public static final Tile[] pathToBank = {new Tile(3209, 3261, 0), new Tile(3214, 3261, 0), new Tile(3214, 3257, 0), new Tile(3216, 3253, 0), new Tile(3216, 3249, 0), new Tile(3217, 3245, 0), new Tile(3219, 3241, 0), new Tile(3223, 3238, 0), new Tile(3226, 3235, 0), new Tile(3227, 3231, 0), new Tile(3230, 3227, 0), new Tile(3231, 3222, 0), new Tile(3227, 3219, 0), new Tile(3223, 3219, 0), new Tile(3219, 3219, 0), new Tile(3215, 3218, 0), new Tile(3215, 3214, 0), new Tile(3212, 3211, 0), new Tile(3208, 3210, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2), new Tile(3209, 3220, 2)};
    private final Walker walker = new Walker(ctx);


    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>27 || (ctx.inventory.select().count()<28 && pathToBank[0].distanceTo(ctx.players.local())<6);
    }

    @Override
    public void execute() {
            System.out.println("excecuting walk");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 10) {
            if(ctx.inventory.select().count()>27) {
                walker.walkPath(pathToBank);
            }  else {
                walker.walkPathReverse(pathToBank);
            }
        }

    }
}
