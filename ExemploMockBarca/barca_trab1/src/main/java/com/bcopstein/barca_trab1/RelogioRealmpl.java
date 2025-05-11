package com.bcopstein.barca_trab1;

import java.time.LocalDateTime;

public class RelogioRealmpl implements Relogio {    

    @Override
    public int getHora() {
        return LocalDateTime.now().getHour();
    }

    @Override
    public int getMinuto() {
        return LocalDateTime.now().getMinute();
    }
    
}
