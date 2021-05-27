"use strict";
const directiveLiteral = "worklet";
const blacklist = [];

module.exports = function ({ types: t }) {
    return {
        visitor: {
            'FunctionDeclaration|FunctionExpression|ArrowFunctionExpression': {
                exit(path, _state) {
                    const fun = path;
                    if (fun.node.body.directives !== undefined){
                        const directive = t.directive(t.directiveLiteral(directiveLiteral));
                         fun.node.body.directives.push(directive);
                    }
                 }
             }
        }
    };
};
