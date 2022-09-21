package uk.ac.le.cs.CO309;

import org.w3c.dom.*;
import org.apache.xerces.parsers.DOMParser;

public class WebAppParser {

	public static void main(String[] args) {
		try {
			DOMParser parser = new DOMParser();
			parser.parse("WebApp.xml");
			Document doc = parser.getDocument();
			System.out.println("{");
			traverse_tree(doc);
			System.out.println("}");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static boolean checkIfChildExists(Node node) {
		if (node.getChildNodes().getLength() > 1) {
			return true;
		}
		return false;
	}

	public static void traverse_Inner_tree(Node node, int index) {
		NodeList child = node.getChildNodes();
		if (node.getLocalName() == "parameters") {
			System.out.println("		 	" + "\"" + node.getLocalName() + "\" : [");
			for (int i = 0; i < child.getLength(); i++) {
				if (child.item(i) != null && child.item(i).getLocalName() != null
						&& child.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element childElement1 = (Element) child.item(i);
					NamedNodeMap att = childElement1.getAttributes();
					System.out.println("				{");
					System.out.println("					" + "\"" + "var" + "\" : " + "\""
							+ child.item(i).getTextContent() + "\"" + ",");
					System.out.println("					" + "\"" + att.item(0).getLocalName() + "\"" + " : " + "\""
							+ att.item(0).getTextContent() + "\"");
					if (child.getLength() - 2 <= i) {
						System.out.println("				}");
					} else {
						System.out.println("				},");
					}
				}
			}
		} else {
			NodeList ch = node.getChildNodes();
			System.out.println("		 	" + "\"" + "exceptions" + "\"" + " : [ ");
			for (int i = 0; i < ch.getLength(); i++) {
				if (ch.item(i) != null && ch.item(i).getLocalName() != null
						&& ch.item(i).getNodeType() == Node.ELEMENT_NODE) {
					if (ch.getLength() - 2 > i) {
						System.out.println("				" + "\"" + child.item(i).getTextContent() + "\",");
					} else {
						System.out.println("				" + "\"" + child.item(i).getTextContent() + "\"");

					}
				}
			}
		}
		System.out.println("			],");
	}

	public static void traverse_tree(Node node) {
		if (node == null)
			return;
		int type = node.getNodeType();
		switch (type) {

		case Node.DOCUMENT_NODE:
			traverse_tree(((Document) node).getDocumentElement());
			break;

		case Node.ELEMENT_NODE:
			NodeList mainNode = node.getChildNodes();
			if (mainNode != null) {
				Element mainElement = (Element) mainNode;
				NodeList nodeTag = mainElement.getElementsByTagName("abstract_method");
				System.out.println("	" + "\"" + nodeTag.item(type).getLocalName() + "\"" + " : [");
				for (int parentIndex = 0; parentIndex <= nodeTag.getLength() - 1; parentIndex++) {
					System.out.println("		{");
					NodeList child = nodeTag.item(parentIndex).getChildNodes();
					NamedNodeMap attributes = nodeTag.item(parentIndex).getAttributes();
					System.out.println("			\"" + attributes.item(0).getLocalName() + "\" : " + "\""
							+ ((Element) child).getAttribute(attributes.item(0).getLocalName()) + "\",");
					for (int childIndex = 0; childIndex <= child.getLength() - 1; childIndex++) {
						if (child.item(childIndex).getLocalName() != null) {
							if (checkIfChildExists(child.item(childIndex))) {
								traverse_Inner_tree(child.item(childIndex), childIndex);
							} else {
								if (childIndex >= child.getLength() - 2) {
									System.out.println("			\"" + child.item(childIndex).getLocalName()
											+ "\" : " + "\"" + child.item(childIndex).getTextContent() + "\"");
								} else {
									System.out.println("			\"" + child.item(childIndex).getLocalName()
											+ "\" : " + "\"" + child.item(childIndex).getTextContent() + "\",");
								}
							}
						}
					}
					if (parentIndex >= nodeTag.getLength() - 1) {
						System.out.println("		}");
					} else {
						System.out.println("		},");
					}
				}
				System.out.println("	]");
			}
			break;
		}
	}
}
