package net.bplaced.clayn.cfs;

import java.io.FileFilter;
import java.util.function.Predicate;

/**
 * A filter clas similiar to {@link FileFilter} but for
 * {@link SimpleFile SimpleFiles}. This interface also works as
 * {@link Predicate} i.e. you may use it for streams or other functional
 * methods.
 *
 * @author Clayn
 * @since 0.1
 */
@FunctionalInterface
public interface SimpleFileFilter extends Predicate<SimpleFile>
{

    /**
     * Returns true if the file should be accepted or false otherwise.
     *
     * @param sf the file to check
     * @return {@code true} if the file is acceptable, {@code false} otherwise
     * @see #test(net.bplaced.clayn.cfs.SimpleFile)
     * @since 0.1
     */
    public boolean accept(SimpleFile sf);

    /**
     * {@inheritDoc }
     *
     * @param t {@inheritDoc }
     * @return {@inheritDoc }
     * @see #accept(net.bplaced.clayn.cfs.SimpleFile)
     * @since 0.1
     */
    @Override
    public default boolean test(SimpleFile t)
    {
        return accept(t);
    }

    /**
     * Returns a SimpleFileFilter that accepts all files. This can be used to
     * recieve all files from a directory.
     *
     * @return a new filefilter that will accept all files
     * @see #extensionFilter(java.lang.String)
     * @see #nameFilter(java.lang.String)
     * @since 0.1
     */
    public static SimpleFileFilter all()
    {
        return (sf) -> true;
    }

    /**
     * Returns a SimpleFileFilter that only accepts files that end with the
     * given extension.
     *
     * @param ext the extentions to check for
     * @return a new filefilter that will only accept files with the given
     * extension
     * @see #all()
     * @see #nameFilter(java.lang.String)
     * @see String#endsWith(java.lang.String) 
     * @since 0.1
     */
    public static SimpleFileFilter extensionFilter(String ext)
    {
        return (sf) -> sf.getName().endsWith(ext);
    }

    /**
     * Returns a SimpleFileFilter that only accepts files that match the given
     * name. Files are accepted when the filename contains the given one. It
     * does not have to match exactly but is case sensitive.
     *
     * @param name the name to search for.
     * @return a new filefilter that will only accept files that contain the
     * given name
     * @see #all()
     * @see #extensionFilter(java.lang.String)
     * @see String#contains(java.lang.CharSequence) 
     * @since 0.1
     */
    public static SimpleFileFilter nameFilter(String name)
    {
        return (sf) -> sf.getName().contains(name);
    }
}
