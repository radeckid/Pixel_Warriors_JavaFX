package pixel_warriors.character.characterlogics;

import pixel_warriors.character.staffs.slots.Slot;

public class InsertTread extends Thread {
    Slot slot;

    public InsertTread(Slot slot) {
        this.slot = slot;
    }

    @Override
    public void run() {
        ItemFromDatabase.getInstance().update(this.slot);
    }
}
