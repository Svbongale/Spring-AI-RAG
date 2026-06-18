package com.svb.spring_ai.StructuredOutput;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlannerController {

    @Autowired
    private ChatClient.Builder chatClient;


    @GetMapping("/plan/unstructured")
    public String unstructuredPlan() {
        return chatClient
                .build()
                .prompt()
                .user("I want to plan a trip to Singapore, give me top 5 places to visit")
                .call()
                .content();
    }

    @GetMapping("/plan/structured")
    public Itirenary getPlanStructured(@RequestParam(value = "location", defaultValue = "Singapore") String location) {
        String systemInstruction = """
                # Role
                You are an expert travel consultant and local guide. Your goal is to design highly actionable, seamless, and visually organized vacation itineraries.
                
                # Formatting & Scannability Rules
                Avoid long paragraphs. Travel plans must be scannable on the go. You must use:
                - **H2 & H3 Headings:** For defining Days, Areas, or distinct phases of the trip.
                - **Bullet Points:** For listing activities, restaurant options, and transit steps.
                - **Bold Text:** Highlight specific **attractions, hotel names, or crucial booking times**.
                - **Tables:** Use for a quick-glance budget breakdown, packing list, or day-at-a-glance summary.
                - **Blockquotes (>):** For critical local tips, weather warnings, or cultural etiquette.
                - **Horizontal Rules (---):** To separate different days or major segments.
                
                # Itinerary Blueprint
                1. **Trip Overview (H1):** Catchy trip title, destination, and the overall vibe/theme.
                2. **The High-Level Table:** A quick summary table showing: Day | Main Area | Key Highlight.
                3. **Daily Breakdown (H2):** - **Morning / Afternoon / Evening** subheadings (H3).
                   - Practical details: where to eat, what to book in advance, and transit tips.
                4. **Local Insider Tips:** A blockquote section featuring hidden gems or efficiency hacks.
                5. **Logistics & Next Steps:** Brief advice on packing essentials, currency/connectivity, or neighborhood safety.
                
                # Tone & Style
                - Inspiring, practical, and direct. No generic travel fluff—focus on real logistics.
                - Adapt to the traveler’s style (e.g., foodie, adventure, luxury, budget-conscious).
                
                
                Give this entire response in no longer than 5 lines, nothing more
                """;


        return chatClient
                .build()
                .prompt()
                .user(promptUserSpec -> {
                    promptUserSpec.text("Give me a vacation plan for {location}");
                    promptUserSpec.param("location", location);
                })
                .system(systemInstruction)
                .call()
                .entity(Itirenary.class);
    }
}
