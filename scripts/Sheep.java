package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.tasks.Bank;
import scripts.tasks.Sheer;
import scripts.tasks.Walk;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="SheepShear", description = "Shears Sheep at Lumbridge for money", properties = "author=samlol; client=4;")
public class Sheep extends PollingScript<ClientContext>{

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){

        taskList.add(new Bank(ctx));
        taskList.add(new Walk(ctx));
        taskList.add(new Sheer(ctx));

    }

    @Override
    public void poll() {

       for(Task task : taskList){
        if(task.activate()){
            task.execute();
            break;
        }
    }
}
}

