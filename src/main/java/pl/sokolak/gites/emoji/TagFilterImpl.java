package pl.sokolak.gites.emoji;

import pl.sokolak.gites.tag.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TagFilterImpl implements TagFilter {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Emoji> findAllContainingTagPhrases(Set<Tag> tags) {

        String row = "CONCAT(LOWER(e.name), LOWER(STRING_AGG(t.name, ';')))";

        String conditions = tags.stream()
                .map(Tag::getName)
                .map(s -> row + " LIKE '%" + s + "%'")
                .collect(Collectors.joining(" AND "));

        String subSql = """
                    SELECT e.name FROM Emoji e
                    LEFT OUTER JOIN Emoji_tag et ON et.emojis_name = e.name
                    LEFT OUTER JOIN Tag t ON et.tags_name = t.name
                    GROUP BY e.name
                    HAVING
                     """ + conditions;

        String sql = "SELECT * FROM Emoji e " +
                "WHERE e.name IN (" + subSql + ") " +
                ";";

        Query query = em.createNativeQuery(sql, Emoji.class);
        return new ArrayList<Emoji>(query.getResultList());
    }
}
