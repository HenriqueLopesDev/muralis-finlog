import React from "react";

export interface BaseLabelProps
  extends React.DetailedHTMLProps<
    React.LabelHTMLAttributes<HTMLLabelElement>,
    HTMLLabelElement
  > {
  title: string;
  requiredIndicator?: boolean;
}
