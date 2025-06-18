package com.mars.portal.backend.service;

import java.io.File;
import java.io.IOException;

public interface Compressor {
    File compress(File inputFile) throws IOException;
    File decompress(File inputFile) throws IOException;
}
