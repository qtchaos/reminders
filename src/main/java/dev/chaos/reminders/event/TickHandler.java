package dev.chaos.reminders.event;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.advancement.Advancement;
import net.minecraft.util.Identifier;

import java.util.Map;

import static dev.chaos.reminders.SharedData.REMIND_IN_TICKS;
import static dev.chaos.reminders.SharedData.REMIND_MESSAGE;
import static dev.chaos.reminders.utilities.ChatLogging.log;

public class TickHandler {
    public static CriterionConditions conditions = new CriterionConditions() {
        @Override
        public Identifier getId() {
            return null;
        }

        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            return null;
        }
    };

    public static String[][] requirements = {{""}};

    public static void notifyClient(MinecraftClient client) {
        AdvancementToast toast = new AdvancementToast(
            new Advancement(
                new Identifier("reminders", "reminder"),
                null,
                new AdvancementDisplay(
                    new ItemStack(() -> Item.BLOCK_ITEMS.get(Blocks.BELL)),
                    Text.of(REMIND_MESSAGE),
                    Text.of(REMIND_MESSAGE),
                    null,
                    AdvancementFrame.GOAL,
                    false,
                    false,
                    true
                ),
                new AdvancementRewards(0, new Identifier[0], new Identifier[0], new CommandFunction.LazyContainer((Identifier) null)),
                Map.of("reminders", new AdvancementCriterion(conditions)),
                requirements
            )
        );

        log(REMIND_MESSAGE);
        client.getToastManager().add(toast);
        client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 2F));
    }
    public static void register() {
        ClientTickEvents.START_CLIENT_TICK.register((client) -> {
            if (REMIND_IN_TICKS > 0) {
                REMIND_IN_TICKS--;
                if (REMIND_IN_TICKS == 0) {
                    notifyClient(client);
                }
            }
        });
    }
}
