package dk.sdu.mmmi.cbse.playersystem;


import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PlayerPluginTest {

    private static GameData mockedGameData;
    private static World mockedWorld;
    private static PlayerPlugin playerPlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        playerPlugin = new PlayerPlugin();
    }

    @Test
    void start() {
        playerPlugin.start(mockedGameData, mockedWorld);

        verify(mockedWorld).addEntity(any(Player.class));
    }
}