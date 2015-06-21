package com.github.mkopylec.rpggame;

import com.github.mkopylec.rpggame.domain.characters.BattleSimulator;
import com.github.mkopylec.rpggame.domain.characters.Enemy;
import com.github.mkopylec.rpggame.domain.characters.EnemyFactory;
import com.github.mkopylec.rpggame.domain.characters.Hero;
import com.github.mkopylec.rpggame.domain.world.Dimension;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class RpgGameApplication {

    public static void main(String[] args) {
//        run(RpgGameApplication.class, args);
        BattleSimulator battleSimulator = new BattleSimulator();
        battleSimulator.pursueBattle(new Hero("www"), new EnemyFactory().createEnemy(new Dimension(2, 2)));
    }
}
