package com.tinkoff.skipper.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table (name = "mentor_info")
public class MentorInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;
    private String description;
    //private BigDecimal rating; //TODO: добавить систему просчёта рейтинга
    private String workExperience;
    private String education;
    private String speciality;
    private String documentURLs; //TODO: изменить тип данных


    @OneToMany(fetch = FetchType.LAZY)
    private Set<LessonTemplateEntity> lessonTemplates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "name") //NOTE: в связи только имя, не id
    private CategoryEntity category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mentor_tags",
            joinColumns = @JoinColumn (name = "mentor_id"),
            inverseJoinColumns = {
                @JoinColumn (name = "tag_id", referencedColumnName = "id")
            })
    private Set<TagEntity> tags = new HashSet<>();

    public void addDocumentUrl(String url) {
        documentURLs+=url.trim() + ";";
    }
    public void removeDocumentUrl(String url) {
        if (StringUtils.isNotBlank(documentURLs)) {
            documentURLs.replace(url + ";", "");
        }
    }
    public void clearDocumentUrls() {
        documentURLs = "";
    }

    public void addTag(TagEntity tag) {
        this.tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        this.tags.remove(tag);
    }

    public void clearTags() {
        this.tags.clear();
    }

}