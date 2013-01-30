package pl.edu.agh.iisg.timeline.model;

import static com.google.common.base.Objects.equal;
import static java.util.Objects.hash;

import java.util.Date;

import com.google.common.base.Preconditions;

public class Element implements Comparable<Element> {

    private Axis axis;

    private String title;

    private String description;

    private Long date;

    public Element(Axis axis, String name, String description, Long date) {
        this.axis = axis;
        this.title = name;
        this.description = description;
        this.date = date;
    }

    public Axis getAxis() {
        return axis;
    }

    public void setAxis(Axis owner) {
        Preconditions.checkNotNull(owner);
        this.axis = owner;
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

    protected void validate() {
        Preconditions.checkNotNull(axis);
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(date);
    }

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
        return axis.hashCode() - o.axis.hashCode();
    }

    @Override
    public boolean equals(Object arg) {
        if (arg instanceof Element) {
            final Element that = (Element)arg;
            return equal(axis, that.axis) && equal(title, that.title) && equal(description, that.description) && equal(date, that.date);
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return hash(axis, title, description, date);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Axis axis = null;

        private String title = null;

        private String description = null;

        private Long date = null;

        public Builder axis(Axis owner) {
            this.axis = owner;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
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

        public Element build() {
            return new Element(axis, title, description, date);
        }
    }
}
