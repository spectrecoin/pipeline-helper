// ----------------------------------------------------------------------------
// SPDX-FileCopyrightText: © 2020 Alias Developers
// SPDX-FileCopyrightText: © 2018 SpectreCoin Developers
//
// SPDX-License-Identifier: MIT
//
// @author Yves Schumann <yves@alias.cash>
//
// ----------------------------------------------------------------------------

def call(Map params = [:]) {
    String archiveLocation = params.get("archiveLocation")
    String archiveName = params.get("archiveName")

    // If directory 'Spectrecoin' exists from brevious build, remove it
    def exists = fileExists "${WORKSPACE}/windows/content/Spectrecoin"
    if (exists) {
        fileOperations([
                folderDeleteOperation(
                        folderPath: "${WORKSPACE}/windows/content/Spectrecoin"),
        ])
    }

    exists = fileExists "${archiveLocation}/${archiveName}"

    if (exists) {
        echo "Archive '${archiveName}' found, extracting it..."
        fileOperations([
                fileUnZipOperation(
                        filePath: "${archiveLocation}/${archiveName}",
                        targetLocation: "${WORKSPACE}/windows/content")
        ])
    } else {
        error "Archive '${archiveName}' not found, nothing to do"
    }
}
