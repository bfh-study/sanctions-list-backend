package com.github.bfh.study.slb.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * Represents a sanction entity. It holds all information about a person, organisation or entity.
 *
 * @author Samuel Ackermann
 */
@Entity
@Indexed
@AnalyzerDef( name = "searchAnalyzer",
    tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(
            factory = EdgeNGramFilterFactory.class,
            params = {
                @Parameter(name = "minGramSize", value = "3"),
                @Parameter(name = "maxGramSize", value = "50")
            }
        )
    })
@Analyzer(definition = "searchAnalyzer")
public class SanctionEntity extends SanctionBaseEntity {

    @Transient
    @JsonIgnore
    private String sanctionIdentifier;

    @Field(store = Store.YES)
    private String country;

    @IndexedEmbedded
    @OneToMany(cascade = CascadeType.ALL)
    private List<EntityInfo> entityInfoList;

    @IndexedEmbedded
    @OneToMany(cascade = CascadeType.ALL)
    private Set<BirthInfo> birthInfoList;

    @IndexedEmbedded
    @OneToMany(cascade = CascadeType.ALL)
    private Set<PassportInfo> passportInfoSet;

    @IndexedEmbedded
    @ManyToOne
    private SanctionProgram sanctionProgram;

    /**
     * constructor
     *
     * @param sourceId internal source identifier
     * @param sanctionIdentifier identifier of a sanction program. Is an internal number.
     * @param country country of this entity.
     */
    public SanctionEntity(int sourceId, String sanctionIdentifier, String country) {
        super(sourceId);
        this.sanctionIdentifier = sanctionIdentifier;
        this.country = country;
        entityInfoList = new ArrayList<>();
        birthInfoList = new HashSet<>();
        passportInfoSet = new HashSet<>();
    }

    public SanctionEntity() {
        this(0, "", "");
    }

    public String getSanctionIdentifier() {
        return sanctionIdentifier;
    }

    /**
     * Adds new information about this entity.
     *
     * @param entityInfo entity information
     */
    public void addEntityInfo(EntityInfo entityInfo) {
        entityInfoList.add(entityInfo);
    }

    /**
     * Adds birth information.
     *
     * @param birthInfo birth information
     */
    public void addBirthInfo(BirthInfo birthInfo) {
        birthInfoList.add(birthInfo);
    }

    /**
     * Adds passport information.
     *
     * @param passportInfo passport information
     */
    public void addPassportInfo(PassportInfo passportInfo) {
        passportInfoSet.add(passportInfo);
    }

    /**
     * Adds sanction program.
     *
     * @param sanctionProgram sanction program
     */
    public void setSanctionProgram(SanctionProgram sanctionProgram) {
        this.sanctionProgram = sanctionProgram;
    }

    public String getCountry() {
        return country;
    }

    public List<EntityInfo> getEntityInfoList() {
        return entityInfoList;
    }

    public Set<BirthInfo> getBirthInfoList() {
        return birthInfoList;
    }

    public Set<PassportInfo> getPassportInfoSet() {
        return passportInfoSet;
    }

    public SanctionProgram getSanctionProgram() {
        return sanctionProgram;
    }

    /**
     * returns all searchable fields.
     *
     * @return all searchable fields
     */
    public static String[] getSearchFields() {
        String[] fields = {
            "country",
            "entityInfoList.firstName",
            "entityInfoList.lastName",
            "entityInfoList.wholeName",
            "birthInfoList.place",
            "birthInfoList.country",
            "passportInfoSet.number",
            "passportInfoSet.country"
        };

        return fields;
    }
}
