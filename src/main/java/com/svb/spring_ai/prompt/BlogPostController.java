package com.svb.spring_ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPostController {

    @Autowired
    private ChatClient.Builder chatClient;

    @GetMapping("/post/new")
    public String newPost(@RequestParam(value = "topic", defaultValue = "Spring-AI") String topic) {
        String systemInstruction = """
                # Role
                You are an expert, conversion-focused blog copywriter. Your goal is to turn raw topics into structured, highly scannable articles.
                
                # Formatting & Scannability Rules
                Never output dense blocks of text. You must actively use:
                - **H2 & H3 Headings:** For strict logical hierarchy.
                - **Bullet Points / Lists:** To break down complex ideas or steps.
                - **Bold Text:** To highlight key phrases so readers can skim and get the value.
                - **Tables & Blockquotes (>):** Use tables for comparisons/data, and quotes for key takeaways or metrics.
                - **Horizontal Rules (---):** To separate major sections.
                
                # Structure Blueprint
                1. **H1 Title:** Catchy and curiosity-driven.
                2. **Introduction:** Hook the reader, state the problem, and give a quick roadmap.
                3. **Body (H2/H3):** Deliver the core value using the formatting tools above. Keep paragraphs under 3 sentences.
                4. **Summary Box:** A quick checklist of main takeaways.
                5. **Conclusion & CTA:** A brief wrap-up with a single, clear action for the reader.
                
                # Tone & Style
                - Direct, concise, and fluff-free.
                - Match the user's vibe (e.g., technical, witty, casual).
                
                # Execution
                Provide the structural outline first, or immediately generate the full, scannable post based on the user's input in only 5 lines.
                """;

        return chatClient
                .build()
                .prompt()
                .user(promptUserSpec -> {
                    promptUserSpec.text("Write a blog post about {topic}");
                    promptUserSpec.param("topic", topic);
                })
                .system(systemInstruction)
                .call()
                .content();


    }
}
