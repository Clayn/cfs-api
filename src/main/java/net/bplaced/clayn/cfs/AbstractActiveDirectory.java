/*
 * Copyright (C) 2016 Clayn <clayn_osmato@gmx.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.bplaced.clayn.cfs;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * An abstraction for implementations of the
 * {@link ActiveDirectory active directory} interface. This class manages the
 * required consumers for the events for an active directory. Also it may
 * provide helpfull methods such as dispatching occurences of events.
 * <br>
 * This class also ensures you that the consumers are never {@code null}, if not
 * set to {@code null} by a subclass.
 *
 * @author Clayn
 * @since 0.1
 * @version $Revision: 318 $
 */
public abstract class AbstractActiveDirectory implements ActiveDirectory
{

    private static final Consumer<FileModification> EMPTY_CONSUMER = (sf)
            -> 
            {
    };
    protected Consumer<FileModification> onCreate = EMPTY_CONSUMER;
    protected Consumer<FileModification> onDelete = EMPTY_CONSUMER;
    protected Consumer<FileModification> onModification = EMPTY_CONSUMER;

    /**
     * Sets the consumer that handles create events.
     *
     * @param onCreate the consumer that recieves the create event. May be
     * {@code null}
     * @see ActiveDirectory#setOnCreate(java.util.function.Consumer)
     * @since 0.1
     */
    @Override
    public void setOnCreate(Consumer<FileModification> onCreate)
    {
        this.onCreate = Optional.ofNullable(onCreate).orElse(EMPTY_CONSUMER);
    }

    /**
     * Sets the consumer that handles delete events.
     *
     * @param onDelete the consumer that recieves the delete event. May be
     * {@code null}
     * @see ActiveDirectory#setOnDelete(java.util.function.Consumer)
     * @since 0.1
     */
    @Override
    public void setOnDelete(Consumer<FileModification> onDelete)
    {
        this.onDelete = Optional.ofNullable(onDelete).orElse(EMPTY_CONSUMER);
    }

    /**
     * Sets the consumer that handles modification events.
     *
     * @param changedFile the consumer that recieves the modification event. May
     * be {@code null}
     * @see ActiveDirectory#setOnModify(java.util.function.Consumer)
     * @since 0.1
     */
    @Override
    public void setOnModify(Consumer<FileModification> changedFile)
    {
        this.onModification = Optional.ofNullable(changedFile).orElse(
                EMPTY_CONSUMER);
    }

    /**
     * Sends the filemodification to the consumer for that modification.
     *
     * @param mod the modification to dispatch
     * @since 0.1.0
     */
    protected void dispatchModification(FileModification mod)
    {
        switch (mod.getModification())
        {
            case CREATE:
                onCreate.accept(mod);
                break;
            case DELETE:
                onDelete.accept(mod);
                break;
            case MODIFY:
                onModification.accept(mod);
                break;
        }
    }

}
