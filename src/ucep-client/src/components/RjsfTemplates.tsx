import {
  FieldErrorProps,
  FieldTemplateProps,
  getInputProps,
  getSubmitButtonOptions,
  ObjectFieldTemplateProps,
  SubmitButtonProps,
  TitleFieldProps,
  WidgetProps,
} from "@rjsf/utils";
import type { FC } from "react";

export const ObjectFieldTemplate: FC<ObjectFieldTemplateProps> = (props) => {
  return (
    <div className="flex flex-col gap-1">
      {/*border-2 border-indigo-600*/}
      {/* <div>
        {props.title}
        {props.description}
      </div> */}
      {props.properties.map((element, index) => (
        <div key={1000 + index} className="property-wrapper">
          {element.content}
        </div>
      ))}
    </div>
  );
};

export const TitleFieldTemplate: FC<TitleFieldProps> = (props) => {
  return (
    <header id={props.id} className="border-2 border-indigo-600">
      {props.title}
      {props.required && <mark>*</mark>}
    </header>
  );
};

export const FieldTemplate: FC<FieldTemplateProps> = (props) => {
  if (props.id === "root") {
    return (
      <div className={`${props.classNames} flex flex-col gap-2`}>
        <label htmlFor={props.id} className="font-bold">
          {props.label}
          {props.required ? "*" : null}
        </label>
        {props.description}
        {props.children}
        {props.errors}
        {props.help}
      </div>
    );
  }
  return (
    <div className={`${props.classNames} flex flex-row gap-2`}>
      <label htmlFor={props.id} className="w-1/2">
        {props.label}
        {props.required ? "*" : null}
      </label>
      <div className="w-1/2 flex flex-col justify-center">
        {props.description}
        {props.children}
        {props.errors}
        {props.help}
      </div>
    </div>
  );
};

export const FieldErrorTemplate: FC<FieldErrorProps> = (props) => {
  const errorsLength = props.errors?.length || 0;
  if (errorsLength <= 0) {
    return <></>;
  }
  return (
    <details id={props.idSchema.$id}>
      <summary>Errors</summary>
      <ul>
        {props.errors?.map((error) => (
          <li key={Math.random()} className="error">
            {error}
          </li>
        ))}
      </ul>
    </details>
  );
};

export const BaseInputTemplate: FC<WidgetProps> = (props) => {
  const {
    schema,
    id,
    options,
    label,
    value,
    type,
    placeholder,
    required,
    disabled,
    readonly,
    autofocus,
    onChange,
    onBlur,
    onFocus,
    rawErrors,
    hideError,
    uiSchema,
    registry,
    formContext,
    ...rest
  } = props;
  const onTextChange = ({
    target: { value: val },
  }: React.ChangeEvent<HTMLInputElement>) => {
    // Use the options.emptyValue if it is specified and newVal is also an empty string
    onChange(val === "" ? options.emptyValue || "" : val);
  };
  const onTextBlur = ({
    target: { value: val },
  }: React.FocusEvent<HTMLInputElement>) => onBlur(id, val);
  const onTextFocus = ({
    target: { value: val },
  }: React.FocusEvent<HTMLInputElement>) => onFocus(id, val);

  const inputProps = { ...rest, ...getInputProps(schema, type, options) };
  const hasError = (rawErrors ? rawErrors.length > 0 : false) && !hideError;

  return (
    <input
      className={`px-3 py-1.5 text-base font-normal
                text-gray-700 bg-white bg-clip-padding border border-solid
                border-gray-300 rounded transition ease-in-out focus:text-gray-700
                focus:bg-white focus:border-blue-600 focus:outline-none
                ${hasError ? "border-red-600" : ""}`}
      id={id}
      value={value}
      placeholder={placeholder}
      disabled={disabled}
      readOnly={readonly}
      autoFocus={autofocus}
      onChange={onTextChange}
      onBlur={onTextBlur}
      onFocus={onTextFocus}
      {...inputProps}
    />
  );
};

export const SubmitButton: FC<SubmitButtonProps> = (props) => {
  const {
    submitText,
    norender,
    props: submitButtonProps = {},
  } = getSubmitButtonOptions(props.uiSchema);
  if (norender) {
    return null;
  }
  return (
    <div className="flex flex-row py-2">
      <button
        name="back"
        type="submit"
        className={`px-6 py-2.5 bg-violet-800 text-white
                    font-medium text-xs leading-tight uppercase rounded-3xl
                    shadow-md hover:bg-violet-900 hover:shadow-lg focus:bg-blue-700
                    focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800
                    active:shadow-lg transition duration-150 ease-in-out`}
      >
        Back
      </button>
      <button
        name="next"
        type="submit"
        {...submitButtonProps}
        className={`ml-auto px-6 py-2.5 bg-violet-800 text-white
                    font-medium text-xs leading-tight uppercase rounded-3xl
                    shadow-md hover:bg-violet-900 hover:shadow-lg focus:bg-blue-700
                    focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800
                    active:shadow-lg transition duration-150 ease-in-out`}
      >
        {submitText}
      </button>
    </div>
  );
};
