package pl.sokolak.gites.emoji;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepo extends JpaRepository<Emoji, String> {
}
