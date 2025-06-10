import React from "react";

export interface BaseSelectProps
  extends React.DetailedHTMLProps<
    React.SelectHTMLAttributes<HTMLSelectElement>,
    HTMLSelectElement
  > {
  children: React.ReactNode;
}
