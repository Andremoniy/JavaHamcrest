package org.hamcrest.collection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Matches if collection is truly unmodifiable
 */
public class IsUnmodifiableCollection<E> extends TypeSafeDiagnosingMatcher<Collection<? extends E>> {

    @SuppressWarnings("unchecked")
    @Override
    protected boolean matchesSafely(Collection item, Description mismatchDescription) {
        final Object testObject = new Object();
        final Set<Object> singletonList = Collections.singleton(testObject);

        try {
            item.add(testObject);
            mismatchDescription.appendText("was able to add a value into the collection");
            return false;
        } catch (Exception ignore) {
        }

        try {
            item.addAll(singletonList);
            mismatchDescription.appendText("was able to perform addAll on the collection");
            return false;
        } catch (Exception ignore) {
        }

        try {
            item.remove(testObject);
            mismatchDescription.appendText("was able to remove a value from the collection");
            return false;
        } catch (Exception ignore) {
        }

        try {
            item.removeAll(singletonList);
            mismatchDescription.appendText("was able to perform removeAll on the collection");
            return false;
        } catch (Exception ignore) {
        }

        try {
            item.retainAll(singletonList);
            mismatchDescription.appendText("was able to perform retainAll on the collection");
            return false;
        } catch (Exception ignore) {
        }

        try {
            item.clear();
            mismatchDescription.appendText("was able to clear the collection");
            return false;
        } catch (Exception ignore) {
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected to be unmodifiable collection, but ");
    }

    /**
     * Creates matcher that matches when collection is truly unmodifiable
     */
    public static <E> Matcher<Collection<? extends E>> isUnmodifiable() {
        return new IsUnmodifiableCollection<>();
    }
}
