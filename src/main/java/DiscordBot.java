import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public JDABuilder botJDA;

    public DiscordBot() {

    }

    public void loginBot() {
        botJDA = JDABuilder.createDefault("OTU0OTQ2MTg3Mzc3NjY0MDEw.Yjag8Q.oS4orBfvyuyxVoy86XwGi25t9xU");

        // Disable parts of the cache
        botJDA.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        botJDA.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        botJDA.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        botJDA.setActivity(Activity.watching(" You."));

        // Disable cache for member activities (streaming/games/spotify)
        botJDA.disableCache(CacheFlag.ACTIVITY);

        // Only cache members who are either in a voice channel or owner of the guild
        botJDA.setMemberCachePolicy(MemberCachePolicy.ONLINE.or(MemberCachePolicy.OWNER));

        // Disable member chunking on startup
        botJDA.setChunkingFilter(ChunkingFilter.NONE);

        // Disable presence updates and typing events
        botJDA.disableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_TYPING);

        // Consider guilds with more than 50 members as "large".
        // Large guilds will only provide online members in their setup and thus reduce bandwidth if chunking is disabled.
        botJDA.setLargeThreshold(50);

        try {
            botJDA.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }


}
