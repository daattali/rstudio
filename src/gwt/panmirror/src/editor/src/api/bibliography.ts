/*
 * bibliography.ts
 *
 * Copyright (C) 2020 by RStudio, PBC
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */

import { Node as ProsemirrorNode } from 'prosemirror-model';
import { yamlMetadataNodes, parseYaml, stripYamlDelimeters } from './yaml';

export interface Bibliography {
  sources: BibliographySource[];
}

export interface BibliographySource {
  id: string;
  type: string;
  DOI: string;
  URL: string;
  title: string;
  author: BibliographyAuthor[];
}

export interface BibliographyAuthor {
  family: string;
  given: string;
}

export function bibliographyFileForDoc(doc: ProsemirrorNode) : string | null {

  // TODO: I guess you could technically have a bibliography entry in another yaml node
  // TODO: references can actually be defined an inline yaml as per pandoc docs

  const yamlNodes = yamlMetadataNodes(doc);
  if (yamlNodes.length > 0) {
    const yamlText = yamlNodes[0].node.textContent;
    const yamlCode = stripYamlDelimeters(yamlText);
    const yaml = parseYaml(yamlCode);
    if (yaml && typeof yaml === 'object') {
      return yaml.bibliography || null;
    } else {
      return null;
    }
  } else {
    return null;
  }

}