package pl.edu.agh.iisg.timeline.model;

import static com.google.common.base.Objects.equal;
import static java.util.Objects.hash;

import java.util.Date;

import com.google.common.base.Preconditions;

public class Element implements Comparable<Element> {

    private Axis owner;

    private String title;

    private String description;

    private Long date;

    private Long duration;

    // BEGIN Constructors

    public Element(Axis owner, String name, String description, Long date, Long duration) {
        this.owner = owner;
        this.title = name;
        this.description = description;
        this.date = date;
        this.duration = duration;
        init();
    }

    private void init() {

    }

    // END Constructors

    // BEGIN Getters & Setters

    public Axis getAxis() {
        return owner;
    }

    public void setOwner(Axis owner) {
        Preconditions.checkNotNull(owner);
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Preconditions.checkNotNull(title);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        Preconditions.checkNotNull(date);
        this.date = date;
    }

    public void setDate(Date date) {
        Preconditions.checkNotNull(date);
        this.date = date.getTime();
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    // END Getters & Setters

    // BEGIN Overridden Methods

    @Override
    public int compareTo(Element o) {
        Preconditions.checkNotNull(o);
        int res = date.compareTo(o.date);
        if (res != 0) {
            return res;
        }
        res = title.compareTo(o.title);
        if (res != 0) {
            return res;
        }
        res = description.compareTo(o.description);
        if (res != 0) {
            return res;
        }
        res = duration.compareTo(o.duration);
        if (res != 0) {
            return res;
        }
        return owner.hashCode() - o.owner.hashCode();
    }

    @Override
    public boolean equals(Object arg) {
        if (arg instanceof Element) {
            final Element that = (Element)arg;
            return equal(owner, that.owner) && equal(title, that.title) && equal(description, that.description) && equal(date, that.date)
                    && equal(duration, that.duration);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return hash(owner, title, description, date, duration);
    }

    // END Overridden Methods

    // BEGIN Builder Methods
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Axis owner = null;

        private String name = null;

        private String description = null;

        private Long date = null;

        private Long duration = 0L;

        public Builder owner(Axis owner) {
            this.owner = owner;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder date(Long date) {
            this.date = date;
            return this;
        }

        public Builder date(Date date) {
            this.date = date.getTime();
            return this;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Builder setDuration(Long duration) {
            this.duration = duration;
            return this;
        }

        public Element build() {
            validate();
            return new Element(owner, name, description, date, duration);
        }

        protected void validate() {
            Preconditions.checkNotNull(owner);
            Preconditions.checkNotNull(name);
            Preconditions.checkNotNull(date);
            if (duration != null) {
                Preconditions.checkArgument(duration >= 0);
            }
        }
    }

    // END Builder Methods
}
