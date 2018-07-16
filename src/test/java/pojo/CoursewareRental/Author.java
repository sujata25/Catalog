
package pojo.CoursewareRental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bio",
    "name",
    "id",
    "affiliates",
    "imageUrl"
})
public class Author {

    @JsonProperty("bio")
    private String bio;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("affiliates")
    private List<Object> affiliates = new ArrayList<Object>();
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bio")
    public String getBio() {
        return bio;
    }

    @JsonProperty("bio")
    public void setBio(String bio) {
        this.bio = bio;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("affiliates")
    public List<Object> getAffiliates() {
        return affiliates;
    }

    @JsonProperty("affiliates")
    public void setAffiliates(List<Object> affiliates) {
        this.affiliates = affiliates;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bio).append(name).append(id).append(affiliates).append(imageUrl).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Author) == false) {
            return false;
        }
        Author rhs = ((Author) other);
        return new EqualsBuilder().append(bio, rhs.bio).append(name, rhs.name).append(id, rhs.id).append(affiliates, rhs.affiliates).append(imageUrl, rhs.imageUrl).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
