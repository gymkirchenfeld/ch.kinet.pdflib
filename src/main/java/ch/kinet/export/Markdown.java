/*
 * Copyright (C) 2017 - 2023 by Stefan Rothe, Sebastian Forster
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY); without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.kinet.export;

import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import java.util.Arrays;

public final class Markdown {

    private static final Parser PARSER;
    private static final HtmlRenderer RENDERER;

    static {
        MutableDataSet options = new MutableDataSet()
            .set(Parser.EXTENSIONS, Arrays.asList(
                 TaskListExtension.create(),
                 AutolinkExtension.create(),
                 StrikethroughExtension.create(),
                 DefinitionExtension.create()))
            .set(TaskListExtension.ITEM_DONE_MARKER, "<i class=\"far fa-fw fa-check-square clickable\"></i> ")
            .set(TaskListExtension.ITEM_NOT_DONE_MARKER, "<i class=\"far fa-fw fa-square clickable\"></i> ");

        options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        PARSER = Parser.builder(options).build();
        RENDERER = HtmlRenderer.builder(options).build();
    }

    public static String toMarkdown(String markdown) {
        if (markdown == null) {
            return "";
        }

        Node document = PARSER.parse(markdown);
        return RENDERER.render(document);
    }
}
