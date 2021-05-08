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
//        StringJoiner columns = new StringJoiner(", ';' ,");
//
//        if (columnList.getOrDefault("title", false)) {
//            columns.add("LOWER(b.title)");
//        }
//        if (columnList.getOrDefault("subtitle", false)) {
//            columns.add("LOWER(b.subtitle)");
//        }
//        if (columnList.getOrDefault("seriesVolume", false)) {
//            columns.add("LOWER(b.series_volume)");
//        }
//        if (columnList.getOrDefault("city", false)) {
//            columns.add("LOWER(b.city)");
//        }
//        if (columnList.getOrDefault("year", false)) {
//            columns.add("b.year");
//        }
//        if (columnList.getOrDefault("volume", false)) {
//            columns.add("b.volume");
//        }
//        if (columnList.getOrDefault("edition", false)) {
//            columns.add("LOWER(b.edition)");
//        }
//        if (columnList.getOrDefault("comment", false)) {
//            columns.add("LOWER(b.comment)");
//        }
//        if (columnList.getOrDefault("authors", false)) {
//            columns.add("LOWER(STRING_AGG(CONCAT(a.first_name, a.middle_name, a.last_name, a.prefix), ';'))");
//        }
//        if (columnList.getOrDefault("publishers", false)) {
//            columns.add("LOWER(STRING_AGG(p.name, ';'))");
//        }
//        if (columnList.getOrDefault("series", false)) {
//            columns.add("LOWER(STRING_AGG(s.name, ';'))");
//        }
//
//        String row = "CONCAT(" + columns + ")";
//        String conditions = subPhrases.stream()
//                .map(s -> row + " LIKE '%" + s + "%'")
//                .collect(Collectors.joining(" AND "));
//
//        String subSql = "SELECT b.id FROM Book b " +
//
//                "LEFT OUTER JOIN Book_author ba ON ba.books_id = b.id " +
//                "LEFT OUTER JOIN Author a ON ba.authors_id = a.id " +
//
//                "LEFT OUTER JOIN Book_publisher bp ON bp.books_id = b.id " +
//                "LEFT OUTER JOIN Publisher p ON bp.publishers_id = p.id " +
//
//                "LEFT OUTER JOIN Series s ON s.id = b.series_id " +
//
//                "GROUP BY b.id " +
//                "HAVING " +
//                conditions;
//        String sql = "SELECT * FROM Book b " +
//                "WHERE b.id IN (" + subSql + ") " +
//                "ORDER BY b.id " +
//                "LIMIT " + pageable.getPageSize() +
//                " OFFSET " + pageable.getPageSize() * pageable.getPageNumber();

        String row = "LOWER(STRING_AGG(t.id, ';'))";

        String conditions = tags.stream()
                .map(Tag::getId)
                .map(s -> row + " LIKE '%" + s + "%'")
                .collect(Collectors.joining(" AND "));


        String subSql = """
                    SELECT e.id FROM Emoji e
                    LEFT OUTER JOIN Emoji_tag et ON et.emojis_id = e.id
                    LEFT OUTER JOIN Tag t ON et.tags_id = t.id
                    LEFT OUTER JOIN Emoji_category ec ON ec.emojis_id = e.id
                    LEFT OUTER JOIN Category c ON ec.categories_id = c.id
                    GROUP BY e.id
                    HAVING
                     """ + conditions;

        String sql = "SELECT * FROM Emoji e " +
                "WHERE e.id IN (" + subSql + ");";

        Query query = em.createNativeQuery(sql, Emoji.class);
        return new ArrayList<Emoji>(query.getResultList());
    }
}
