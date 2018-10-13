package com.topnotch.resty.util.stream;

import java.io.File;

/**
 * Implements a renaming policy that adds increasing integers to the body of
 * any file that collides.  For example, if foo.png is being uploaded and a
 * file by the same name already exists, this logic will modify the upload
 * foo1.png.  A second upload by the same name would be foo2.png.
 * Note that for safety the rename() method creates a zero-length file with
 * the chosen name to act as a marker that the name is taken even before the
 * upload starts writing the bytes.
 *
 * @author funmiayinde
 */
public class DefaultFileModifier extends FileModifier {

    // This method does not need synchronized because createNewFile()
    // is atomic and used here mark when a file name is choosen
    public File modify(File file) {
        if (createdFile(file)) {
            return file;
        }
        String name = file.getName();
        String body = null;
        String ext = null;

        int dot = name.lastIndexOf(".");
        if (dot != -1) {
            body = name.substring(0, dot);
            ext = name.substring(dot);
        } else {
            body = "";
            ext = "";
        }

        // Increase the count until an empty spot of found,
        // Max out at 9999 to avoid an infinite loop caused by a persistent
        int count = 0;
        while (!createdFile(file) && count < 9999) {
            count++;
            String rename = body + count + ext;
            file = new File(file.getParent(), rename);
        }
        return file;
    }


}
