package dev.chaos.reminders.utilities;

import com.google.gson.JsonObject;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.block.Blocks;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;


import static dev.chaos.reminders.SharedData.*;
import static dev.chaos.reminders.utilities.Random.getRandomString;

public class Toast {

    protected static CriterionConditions conditions = new CriterionConditions() {
        @Override
        public Identifier getId() {
            return null;
        }

        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            return null;
        }
    };

    protected static String handlePath() {
        if (LOOP_REMINDER) {
            if (STORE_PATH.equals("")) {
                STORE_PATH = getRandomString();
            }
            return STORE_PATH;
        } else {
            return getRandomString();
        }
    }

    protected static String[][] requirements = {{""}};
    public static AdvancementToast useToast() {
        return new AdvancementToast(
            new Advancement(
                // We use a random path so that the toast is getting updated each time.
                new Identifier("reminders", handlePath()),
                null,
                new AdvancementDisplay(
                        new ItemStack(Item.BLOCK_ITEMS.get(Blocks.BELL)),
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
    }
}
